package com.eastpro.util;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class EabMimeMesage extends MimeMessage {
	
   private String messageid;
   private boolean ispos;
   public EabMimeMesage(Session session,String msgid,boolean ispos) {
      super(session);
      this.messageid=msgid;
      this.ispos=ispos;
   }

   @Override
   protected void updateHeaders() throws MessagingException {
      super.updateHeaders();
      if(ispos){
       setHeader("Message-ID", messageid);
      }
   }
}