package com.eastpro.util;


import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import javax.mail.*;
import javax.xml.soap.MimeHeader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;
/**
 * set email common function.
 * Creation date: (4/13/2001 4:09:33 PM)
 * @author: unknow
 * @UpdateHistory    
 * 20100118-jyh - add handle set email addr.
 */
public class HtmlMail {
	public java.lang.String host = null;
	protected java.lang.String from;
	protected java.lang.String to;
	protected java.lang.String subject;
	protected String htmlText;
	protected String[] imgsPath = null;
	public javax.mail.Address invalidAddress[] = null;
	public javax.mail.Address validSentAddress[] = null;
	public javax.mail.Address validUnsentAddress[] = null;
	protected java.lang.String replyTo;
	protected java.lang.String cc = null;
	protected java.lang.String bcc;
	private String mailSize = null;
		protected String contentType = null;   
	//20080306-KELVIN-S
	protected String[] attachFile = null;
	//20080306-KELVIN-E
	public Collection<Map> attachMap =null;
	private Map<String,Object>  ecardImg=null;
	private java.lang.String fromName=null;	
	private java.lang.String isposemail=null;
	private java.lang.String posemailsend=null;
	private java.lang.String messageid=null;
	public java.lang.String getFromName() {
			return fromName;
		}
		public void setFromName(java.lang.String fromName) {
			this.fromName = fromName;
		}
public Map<String, Object> getEcardImg() {
		return ecardImg;
	}
	public void setEcardImg(Map<String, Object> ecardImg) {
		this.ecardImg = ecardImg;
	}
public Collection<Map> getAttachMap() {
		return attachMap;
	}
	public void setAttachMap(Collection<Map> attachMap) {
		this.attachMap = attachMap;
	}
/**
 * HtmlMail constructor comment.
 */
public HtmlMail() {

}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 6:03:40 PM)
 * @return java.lang.String
 */
java.lang.String getBcc() {
	return bcc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 6:03:30 PM)
 * @return java.lang.String
 */
java.lang.String getCc() {
	return cc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:58:59 PM)
 * @return java.lang.String
 */
java.lang.String getReplyTo() {
	return replyTo;
}

public String getContentType() {
	return contentType;
}

public void setContentType(String contentType) {
	this.contentType = contentType;
}

	/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:24:26 PM)
 * replace the <%= _CID %> tag of the original Html
 * text with "CID:xxxx@x"
 *
 */
public void replaceHtml()
{

	int j = 0;

	if (imgsPath != null)
		while (j < imgsPath.length)
		{
			String newPattern = "cid:" + (new Integer(j)).toString() + "@eastpro";
			htmlText = stringReplace(htmlText, "<%= _CID %>", newPattern, false, true);
			j++;
		}


}

/**
 * send email.
 * @return
 * @throws NoSuchProviderException
 * @throws AddressException
 * @throws MessagingException
 * @throws SendFailedException
 */
public boolean send() throws NoSuchProviderException, AddressException, MessagingException,SendFailedException
{
	  Properties props=new Properties ();
	  props.put("mail.smtp.host",host);
	  if (username!=null&&!"".equalsIgnoreCase(username) && password!=null && !"".equalsIgnoreCase(password)){		  	
		  props.put("mail.smtp.auth","true");
	  }else{
		  System.out.println("mail.smtp.auth===========>false");
		  props.put("mail.smtp.auth","false");
	  }
	  
	  props.put("mail.debug","true"); 
     
	  Session session = Session.getDefaultInstance(props,null);
	  session.setDebug(false);
	  Transport transport=session.getTransport("smtp");
	  System.out.println("HtmlMail host = "+host);
	  Message msg = new MimeMessage(session);
/*
	  msg.setFrom(new InternetAddress(from));
	  InternetAddress[] address = {new InternetAddress(to)};
	  msg.setRecipients(Message.RecipientType.TO, address);
*/
		  if (from != null)
				msg.setFrom(new InternetAddress(from));
		  else
				msg.setFrom();

		  if (replyTo != null && !"".equals(replyTo))
				msg.setReplyTo(InternetAddress.parse(replyTo));
		  if (to!=null && !"".equals(to.trim())) {
			  	msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to, false));
		  }
		  if (cc != null)
				msg.setRecipients(Message.RecipientType.CC,
				  InternetAddress.parse(cc, false));
		  if (bcc != null)
				msg.setRecipients(Message.RecipientType.BCC,
				  InternetAddress.parse(bcc, false));

	 
		//msg.setSubject(subject);comment out cxz 20081117
		// add by cxz 20081117 s
		try {
			 msg.setSubject(MimeUtility.encodeText(subject,"UTF-8", "B"));
		} catch (Exception e) {
			 e.printStackTrace();
		} 
		// add by cxz 20081117 e

	  msg.setHeader("X-Mailer", "msgsend");
	  MimeMultipart mp = new MimeMultipart();
	  mp.setSubType("related");
	  MimeBodyPart mbp1= new MimeBodyPart();
		  replaceHtml();
	  if (contentType == null)
		  mbp1.setContent(htmlText,"text/html");
	  else
		  mbp1.setContent(htmlText,contentType);
	  mp.addBodyPart(mbp1);
	  msg.setContent(mp);
	  msg.setSentDate(new Date());

	  if ( imgsPath!=null )
		  for (int i = 0; i < imgsPath.length; i++)
		  {

			  MimeBodyPart mbp2 = new MimeBodyPart();
			  FileDataSource fds = new FileDataSource(imgsPath[i]);
			  try {
				mbp2.setFileName(MimeUtility.encodeText(fds.getName(),"UTF-8", "B"));
			  } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			  }
			  mbp2.setText("image");
			  mbp2.setDataHandler(new DataHandler(fds));
			  String cid = (new Integer(i)).toString() + "@eastpro";
			  mbp2.setHeader("Content-ID",cid);
			  mp.addBodyPart(mbp2);
		  }
	  //if (username!=null && password!=null) {
	  if (username!=null&&!"".equalsIgnoreCase(username) && password!=null && !"".equalsIgnoreCase(password)){
			transport.connect(host, username, password);
	  }
	  else {
			transport.connect();
	  }
	  
	if (replyTo != null && !"".equals(replyTo)){
		if (to!=null && !to.trim().equals("")) {
			transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.TO));
		}
	}

	  if (cc!=null && !cc.trim().equals("")) {
	  	//comment out cxz 20081105 s
	  	/**
		InternetAddress[] old_address = {new InternetAddress(cc)};
		msg.setRecipients(Message.RecipientType.TO, old_address);
		transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.TO));
		**/
		//comment out cxz 20081105 e
		//add by cxz 20081105 s
		InternetAddress[] old_address = InternetAddress.parse(cc);
		msg.setRecipients(Message.RecipientType.CC, old_address);
		transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.CC));
		//add by cxz 20081105 e
	  }
	  //add by cxz 20081105 s
	  if (bcc!=null && !bcc.trim().equals("")) {
	     InternetAddress[] old_address = InternetAddress.parse(bcc);
	     msg.setRecipients(Message.RecipientType.BCC, old_address);
	     transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.BCC));
	  }
      //add by cxz 20081105 e
	  return true;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 6:03:40 PM)
 * @param newBcc java.lang.String
 */
public void setBcc(java.lang.String newBcc) {

	bcc = replaceEmailAddr(newBcc);
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 6:03:30 PM)
 * @param newCc java.lang.String
 */
public void setCc(java.lang.String newCc) {
	cc= replaceEmailAddr(newCc);
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:14:14 PM)
 * @param newFrom java.lang.String
 */
public void setFrom(java.lang.String newFrom) {
	if (newFrom!=null&&!"".equals(newFrom.trim()))
		from = newFrom;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:13:55 PM)
 * @param newHost java.lang.String
 */
public void setHost(java.lang.String newHost) {
	host = newHost;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:17:02 PM)
 * @param newHtmlText int
 */
public void setHtmlText(String newHtmlText) {
	htmlText = newHtmlText;
}
/**  
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:20:21 PM)
 * @param newImgsPath java.util.Vector
 */
public void setImgsPath(String[] newImgsPath) {
	imgsPath = newImgsPath;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:58:59 PM)
 * @param newReplyTo java.lang.String
 */
public void setReplyTo(java.lang.String newReplyTo) {
	replyTo = newReplyTo;
}
/**
 * Insert the method's description here.
 * Creation date: (4/13/2001 4:16:38 PM)
 * @param newSubject java.lang.String
 */
public void setSubject(java.lang.String newSubject) {
	subject = newSubject;
}
/**
 * set TO email addr.
 * Creation date: (4/13/2001 4:15:53 PM)
 * @param newTo java.lang.String
 */
public void setTo(java.lang.String newTo) {
	to = replaceEmailAddr(newTo);
}
//20080306-KELVIN-S
public void setAttachFile(java.lang.String[] newAttachFile) {
	attachFile = newAttachFile;
}
//20080306-KELVIN-E


public static String stringReplace(String str, String oldPattern, String newPattern, boolean replaceAll, boolean ignoreCase) {
	String searchStr = str;
	String patt = oldPattern;
	int maxLen = str.length();

	if (ignoreCase)
	{
		searchStr = str.toUpperCase();
		patt = oldPattern.toUpperCase();
	}

	String newStr = str;
	String result = new String();
	int offset = 0;
	String tmp = "";

	while (searchStr.length() > 0)
	{
		offset = searchStr.indexOf(patt);
		if (offset == -1)
		{
			result = result + newStr;
			break;
		}
		try
		{
			tmp = newStr.substring(0, offset);
		}
		catch (Exception e)
		{
			tmp = "";
		}
		result = result + tmp + newPattern;
		newStr = newStr.substring(offset + oldPattern.length());
		if (!replaceAll)
		{
			result = result + newStr;
			break;
		}
		searchStr = searchStr.substring(offset + patt.length());
	}

	return result;
}
public void setIsposemail(java.lang.String isposemail) {
	this.isposemail = isposemail;
}
public void setPosemailsend(java.lang.String posemailsend) {
	this.posemailsend = posemailsend;
}
public void setMessageid(java.lang.String messageid) {
	this.messageid=messageid;
}
	protected String password;	protected String username;/**
 * Insert the method's description here.
 * Creation date: (2001-11-9 16:02:37)
 * @return javax.mail.PasswordAuthentication
 */
protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(username,password);
}

/**
 * replace ';' to ','
 * @param addr
 * @return
 */
public static String replaceEmailAddr(String addr){
	String value = null;
	String addrTemp = addr;
	if(addrTemp!=null&&!"".equals(addrTemp.trim())){
		while (addrTemp.indexOf(";") != -1) {
			addrTemp = addrTemp.replace(';', ',');
		}
		value = addrTemp;
	}
	return value;
}
/**
 * Insert the method's description here.
 * Creation date: (2001-11-13 9:32:58)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	HtmlMail.sendMail();
}/**
 * only for test send email.
 * Creation date: (2001-11-13 9:29:48)
 * @deprecated
 */
static public void sendMail() {
	  String to ="supershot@163.net";
	  String from ="youqiaoluo@163.net";
	  String server="smtp.163.net";
	  String user="youqiaoluo";
	  String password="";

	  try{
	  Properties props=new Properties ();
	  props.put("mail.smtp.host",server);
	  props.put("mail.smtp.auth","true");
	  props.put("mail.bebug","true");

	  //URLName urlName=new URLName(server);
	  //PasswordAuthentication pa=new PasswordAuthentication(user,password);
	  Session sendMailSession=Session.getDefaultInstance(props,null);
	  sendMailSession.setDebug(true);

	  //sendMailSession.setPasswordAuthentication (urlName,pa);
	  //SMTPTransport transport=new SMTPTransport(sendMailSession,null);
	  Transport transport=sendMailSession.getTransport("smtp");
	  Message msg = new MimeMessage(sendMailSession);
	  msg.setFrom(new InternetAddress(from));
	  InternetAddress[] address = {new InternetAddress(to)};
	  msg.setRecipients(Message.RecipientType.TO, address);
	  msg.setSubject("Java Mail Test");
	  msg.setHeader("X-Mailer", "msgsend");
	  msg.setSentDate(new Date());
	  //msg.setText ("Lalalala......");

	  MimeBodyPart mbp1 = new MimeBodyPart();
	  mbp1.setText("Lalalala.......");

	  // create the second message part
	  MimeBodyPart mbp2 = new MimeBodyPart();

	  // attach the file to the message
	  FileDataSource fds = new FileDataSource("c://msdos.sys");
	  mbp2.setDataHandler(new DataHandler(fds));
	  mbp2.setFileName(fds.getName());

	  // create the Multipart and its parts to it
	  Multipart mp = new MimeMultipart();
	  mp.addBodyPart(mbp1);
	  mp.addBodyPart(mbp2);

	  // add the Multipart to the message
	  msg.setContent(mp);

	  transport.connect (server,user,password);
	  //transport.connect ();
	  transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.TO));
	  }
	  catch(Exception ex){
		ex.printStackTrace();
	  }

}
/**
 * Insert the method's description here.
 * Creation date: (2001-11-9 14:50:36)
 * @param newPassword java.lang.String
 */
public void setPassword(String newPassword) {

	this.password = newPassword;
}/**
 * Insert the method's description here.
 * Creation date: (2001-11-9 14:49:59)
 * @param newUsername java.lang.String
 */
public void setUsername(String newUsername) {
	this.username = newUsername;
}
//20071212-ZLF-S throw all exception
//public boolean sendEmail() throws AddressException, MessagingException {
	public boolean sendEmail() throws Exception {
//20071212-ZLF-E
	
		System.out.println("In mailAgent.sendEmail()" );	
		File fileTemp=null;
		File fileTemp1=null;
				Properties props = System.getProperties();
				props.put("mail.smtp.host", host);	
		        System.out.println("set host ok" );
		       
				// get the default session		    	
				Session session = Session.getInstance(props, null);

				// create a message

				boolean ispos="Y".equals(isposemail)?true:false;
				 
			    EabMimeMesage msg= new EabMimeMesage(session,messageid,ispos);
				
				 if(ispos){
			        	props.put("mail.smtp.from",posemailsend);	
			        	msg.setHeader("Message-ID", messageid);        	
				        System.out.println("--set posemailsend ok");
			     }else{
			        	props.put("mail.smtp.from",from);
			        	System.out.println("---set from ok no pos email");
			     }
				if (from != null){
					if(fromName!=null&&!"".equals(fromName)){
						msg.setFrom(new InternetAddress(from,fromName));
					}else{
						msg.setFrom(new InternetAddress(from));
					}
				  }
				else {
					msg.setFrom();
			    }
	      	System.out.println("set from address ok" );
				if (replyTo != null)
					msg.setReplyTo(InternetAddress.parse(replyTo));
				msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));		
				//20080312-KELVIN-S
				if (cc != null)
					msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
				if(bcc!=null)
					msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
				//20080312-KELVIN-E
					
		//		msg.setRecipients("");
			try {
				 msg.setSubject(MimeUtility.encodeText(subject,"UTF-8", "B"));
			} catch (Exception e) {
				 e.printStackTrace();
			} 
		System.out.println("set subject ok" );
				// create the Multipart
				MimeMultipart mp = new MimeMultipart();

				// create and fill the first message part
				MimeBodyPart mbp1 = new MimeBodyPart();
				replaceHtml();
		System.out.println("replace html ok" );
				//mbp1.setText(this.htmlText,"text/html");
				/*if (contentType == null)
					mbp1.setContent(htmlText,"text/html");
				else
					mbp1.setContent(htmlText,contentType);*/
				mbp1.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>"+htmlText,   
						"text/html;charset=UTF-8"); 
				// add to multipart
				mp.setSubType("related");
				mp.addBodyPart(mbp1);
				//20080306-KELVIN-S
				if (attachFile != null && attachFile.length > 0) {
					for (int i=0; i<attachFile.length; i++) {
						MimeBodyPart mbp2 = new MimeBodyPart();
						FileDataSource fds = new FileDataSource(attachFile[i]);
						mbp2.setDataHandler(new DataHandler(fds));
						mbp2.setFileName(MimeUtility.encodeText(fds.getName(), "big5", "B"));
						mp.addBodyPart(mbp2);
					}
				}
				//20080306-KELVIN-E
				
				
			if(attachMap!=null&&attachMap.size()>0){
				for(Map map: attachMap){
					ByteArrayDataSource bds = null;
					InputStream ins = (InputStream)map.get("APPENDIX");
					bds = new ByteArrayDataSource(ins, "application/octet-stream");
					BodyPart bp = new MimeBodyPart();					
					bp.setDataHandler(new DataHandler(bds));
				    bp.setHeader("Content-Transfer-Encoding", "base64");
					bp.setFileName(MimeUtility.encodeText(map.get("FILENAME").toString(),"UTF-8", "B"));
					mp.addBodyPart(bp);
				} 
			}
	
		System.out.println("add body ok" );
				// add the Multipart to the message
				msg.setContent(mp);
		System.out.println("set content ok" );
				// set the Date: header
				msg.setSentDate(new Date());
		System.out.println("set date ok" );
		//20071220-Gaoyang-S
				if ( imgsPath!=null )
					for (int i = 0; i < imgsPath.length; i++)
					{
		
						MimeBodyPart mbp2 = new MimeBodyPart();
						FileDataSource fds = new FileDataSource(imgsPath[i]);
						mbp2.setFileName(fds.getName());
						mbp2.setText("image");
						mbp2.setDataHandler(new DataHandler(fds));
						String cid = (new Integer(i)).toString() + "@eastpro";
						mbp2.setHeader("Content-ID",cid);
						mp.addBodyPart(mbp2);
					}
		//20071220-Gaoyang-S
				// send the message
		try{
			
			this.mailSize = this.getSize(msg);
			Transport.send(msg);	
			System.out.println("this.mailSize = " + this.mailSize);
			if( fileTemp!=null){
				 fileTemp.delete();
			}
			fileTemp=null;
			if( fileTemp1!=null){
				 fileTemp1.delete();
			}
			fileTemp1=null; 
		}catch(Exception e){
			System.out.println("send message err" );
			e.printStackTrace();
			throw new Exception(e);
			
		}
		
		return true;
		}

	public String getMailSize() {
		return mailSize;
	}
	public String getSize(MimeMessage mimeMessage) {
		String unit = "";
		DecimalFormat df = new DecimalFormat(".00");
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			mimeMessage.writeTo(os);  

			float size = os.size() / 1024.0F;
			if (size != 0)
				if (size / 1024 > 1) {
					unit = df.format(size / 1024.0F) + " M";
				} else {
					unit = df.format(size) + " KB";
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unit;
	}

}