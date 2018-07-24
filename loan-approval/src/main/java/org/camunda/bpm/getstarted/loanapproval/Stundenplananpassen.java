package org.camunda.bpm.getstarted.loanapproval;

public class Stundenplananpassen  implements JavaDelegate{

	 // TODO: Set Mail Server Properties
	  private static final String HOST = "smtp.gmail.com";
	  private static final String USER = "demodemo1234512345@gmail.com";
	  private static final String PWD = "google12345";

	  private final static Logger LOGGER = Logger.getLogger(ArtikelBestellenDelegate.class.getName());

	  public void execute(DelegateExecution execution) throws Exception {
	      String var = (String) execution.getVariable("bezeichnung");
	      String lieferanten = (String) execution.getVariable("professoren");
	      String emailadress = null;
	      if (lieferanten.equals("Rahmenvertrag A")) {
	    		emailadress = "vera.meister@th-brandenburg.de";
	    	}
	      else if (lieferanten.equals("Rahmenvertrag B")) {
	  		emailadress = "vera.meister@th-brandenburg.de";
	  	    }
	      else if (lieferanten.equals("Rahmenvertrag C")) {
	  		emailadress = "vera.meister@th-brandenburg.de";
	     	}
	      String recipient = emailadress ;
	      String etext = "Sehr geehrte Damen und Herren, \n\n Ihr Stundenplan wird nochmal angepasst: " + var + ".\n\n Mit freundlichen Gruessen, \n\n Demo Demo";
	      
	      if (emailadress != null){
	      Email email = new SimpleEmail();
	      email.setCharset("utf-8");
	      email.setHostName(HOST);
	      email.setSmtpPort(465);
	      email.setAuthentication(USER, PWD);
	      email.setSSL(true);
	      
	      try {
	          email.setFrom("noreplykunden@camunda.org");
	          email.setSubject("Artikel Bestellen");
	          email.setMsg(etext);

	          email.addTo(recipient);

	          email.send();
	          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

	        } catch (Exception e) {
	          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
	        }
	      
	      RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
	      runtimeService.correlateMessage("startMessage");
	      runtimeService.startProcessInstanceByMessage("startMessage");
	      } 
	    }

	}

