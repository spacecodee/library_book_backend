package com.spacecodee.library_book_backend.component;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Getter
public class MessageUtilComponent {

    private final MessageSource messageSource;

    public MessageUtilComponent(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message, Object[] obj, String locale) {
        return getMessageSource().getMessage(message, obj, this.getLocaleApp(locale));
    }

    public String getMessage(String message, String locale) {
        return getMessageSource().getMessage(message, null, this.getLocaleApp(locale));
    }

    private Locale getLocaleApp(String locale) {
        return locale.equalsIgnoreCase("en") ? Locale.ENGLISH : Locale.forLanguageTag("es");
    }
}
