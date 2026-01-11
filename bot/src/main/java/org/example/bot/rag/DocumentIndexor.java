package org.example.bot.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Component
public class DocumentIndexor {
    @Value("classpath:/pdfs/rapport.pdf")
    private Resource pdf;
    @Value("store.json")
    private String fileStore;

    @Bean
    public SimpleVectorStore getVectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();
        Path path = Path.of("src", "main", "resources", "store");
        File file = new File(path.toFile(), fileStore);
        if (!file.exists()) {
            if (pdf != null && pdf.exists()) {
                try {
                    PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader(pdf);
                    List<Document> documents = pdfDocumentReader.get();
                    TextSplitter textSplitter = new TokenTextSplitter();
                    List<Document> chunks = textSplitter.split(documents);
                    vectorStore.add(chunks);
                    vectorStore.save(file);
                } catch (Exception e) {
                    System.err.println("Error initializing vector store: " + e.getMessage());
                }
            } else {
                System.err.println("Warning: PDF resource not found or not readable");
            }
        } else {
            try {
                vectorStore.load(file);
            } catch (Exception e) {
                System.err.println("Error loading vector store: " + e.getMessage());
            }
        }
        return vectorStore;
    }
}