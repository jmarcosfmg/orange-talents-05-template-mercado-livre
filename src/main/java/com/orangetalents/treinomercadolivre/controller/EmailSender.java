package com.orangetalents.treinomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.orangetalents.treinomercadolivre.model.PerguntaProduto;
import com.orangetalents.treinomercadolivre.model.Usuario;

@Component
public class EmailSender {

	public void sendPergunta(Usuario usuario, @Valid PerguntaProduto pergunta) {
		System.out.println("Email enviado para " + usuario.getLogin() + "! Assunto: Nova pergunta sobre o produto "
				+ pergunta.getProduto().getNome() + " Mensagem: " + pergunta.getTitulo());
	}
}
