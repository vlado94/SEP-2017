package mail.mail.controllers;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.request.MailRequest;

@RestController
@RequestMapping("/mailController")
public class MailController {

	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping
	public boolean getPDF()  {
	//	sendMail("olja.miljatovic@sep.com", "olja.miljatovic@sep.com", "Polisa :*","Uplacena polisa osiguranja");
		return true;
	}
	
	@PostMapping
	public boolean getPDF(@RequestBody MailRequest response)  {
 		sendMail(response);
		return true;
	}
	
	private void sendMail(MailRequest request ) {
		 try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(request.getFrom());
			helper.setTo(request.getTo());
			helper.setSubject(request.getSubject());
			helper.setText(request.getText() + "Nosilac polise " + request.getName() +  request.getLastName()+"\n Iznos :" + request.getAmount());
			//helper.addAttachment(file.getName(), file);
			 mailSender.send(message);
		 }catch (MessagingException e) {
			   throw new MailParseException(e);
		 }
	}
}
