package cu.edu.cujae.pweb.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JsfUtils {
	public static void addMessage(String componentId, Severity severity, String summary) {
		addMessage(componentId, severity, summary, null);
	}

	public static void addMessage(String componentId, Severity severity, String ...msgs) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, msgs[0], msgs[1]);
		facesContext.addMessage(componentId, message);
	}
	
	public static void addMessageFromBundle(String componentId, Severity severity, String summaryKey) {
		addMessage(componentId, severity, getStringValueFromBundle(summaryKey), null);
	}
	
	public static void addMessageFromBundle(String componentId, Severity severity, String summaryKey, String param) {
		addMessage(componentId, severity, MessageFormat.format(getStringValueFromBundle(summaryKey), param), null);
	}

	public static void addMessageFromBundle(String componentId, Severity severity, String ...keys) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, getStringValueFromBundle(keys[0]), getStringValueFromBundle(keys[1]));
		facesContext.addMessage(componentId, message);
	}
	
	public static String getStringValueFromBundle(String key) {
		return ResourceBundle.getBundle("i18n.language", getCurrentLocale()).getString(key);
	}
	
	public static String getStringValueFromBundleWithParam(String msgKey, String paramValue ) {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n.language", getCurrentLocale());
		String  msgValue = bundle.getString(msgKey);
	    MessageFormat   messageFormat = new MessageFormat(msgValue);
	    Object[] args = {paramValue};
	    return messageFormat.format(args);
	}
	
	public static Locale getCurrentLocale(){
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
}
