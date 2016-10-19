package br.com.listatarefas.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.listatarefas.model.Usuario;
import br.com.listatarefas.services.exception.FalhaDeComunicaoComEmail;

@Service
public class EnviarEmail {

	private JavaMailSender javaMailSender;

	public EnviarEmail(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(Usuario user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("lista.tarefas.pos@gmail.com");
		mail.setSubject("Nova senha de Acesso ao sistema");
		mail.setText("Nova senha para o acesso ao serviço: " + user.getSenha());

		try {
			javaMailSender.send(mail);
		} catch (FalhaDeComunicaoComEmail e) {
			throw new FalhaDeComunicaoComEmail("Falha ao enviar email");
		}
	}
}
