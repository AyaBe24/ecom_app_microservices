import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChatService } from '../../chat.service';
import { LucideAngularModule, Send, Bot, User, Loader2, Trash2 } from 'lucide-angular';

interface Message {
  text: string;
  isUser: boolean;
  timestamp: Date;
}

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './chat.html',
  styleUrl: './chat.css'
})
export class ChatComponent {
  query: string = '';
  messages: Message[] = [];
  isLoading: boolean = false;

  readonly SendIcon = Send;
  readonly BotIcon = Bot;
  readonly UserIcon = User;
  readonly LoaderIcon = Loader2;
  readonly TrashIcon = Trash2;

  constructor(private chatService: ChatService) {
    this.messages.push({
      text: 'Bonjour ! Je suis votre assistant E-com. Comment puis-je vous aider aujourd\'hui ?',
      isUser: false,
      timestamp: new Date()
    });
  }

  sendMessage() {
    if (!this.query.trim() || this.isLoading) return;

    const userQuery = this.query;
    this.messages.push({
      text: userQuery,
      isUser: true,
      timestamp: new Date()
    });

    this.query = '';
    this.isLoading = true;

    this.chatService.ask(userQuery).subscribe({
      next: (response) => {
        this.messages.push({
          text: response,
          isUser: false,
          timestamp: new Date()
        });
        this.isLoading = false;
      },
      error: (err) => {
        console.error(err);
        this.messages.push({
          text: 'Désolé, une erreur est survenue. Veuillez réessayer.',
          isUser: false,
          timestamp: new Date()
        });
        this.isLoading = false;
      }
    });
  }

  clearChat() {
    this.messages = [{
      text: 'Chat réinitialisé. Comment puis-je vous aider ?',
      isUser: false,
      timestamp: new Date()
    }];
  }
}


