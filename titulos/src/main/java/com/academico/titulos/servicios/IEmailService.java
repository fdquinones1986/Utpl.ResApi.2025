package com.academico.titulos.servicios;

import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;

public interface IEmailService {
    public void enviarCorreo(
      String para, String asunto, String contenido);
    
    public void sendHtmlMessage(String to, String subject, String template, Context context) throws MessagingException;
}
