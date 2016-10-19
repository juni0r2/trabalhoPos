package br.com.listatarefas.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import br.com.listatarefas.model.UsuarioFacebook;
import br.com.listatarefas.services.exception.TokenFacebookException;

public class LoginFacebook {

	public UsuarioFacebook obterUsuarioFacebook(String code) throws MalformedURLException, IOException {

		URL url = new URL("https://graph.facebook.com/me?fields=name,email&access_token=" + code);
		String readURL = readURL(url);

		Gson gson = new Gson();
		UsuarioFacebook fromJson = gson.fromJson(readURL, UsuarioFacebook.class);

		return fromJson;
	}

	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			InputStream is = url.openStream();
			int r;
			while ((r = is.read()) != -1) {
				baos.write(r);
			}
		} catch (Exception e) {
			throw new TokenFacebookException("Erro ao autenticar com facebook.");
		}
		return new String(baos.toByteArray());
	}

}