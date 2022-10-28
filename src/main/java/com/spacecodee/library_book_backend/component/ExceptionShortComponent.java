package com.spacecodee.library_book_backend.component;

import com.spacecodee.library_book_backend.exceptions.*;
import org.springframework.stereotype.Component;

@Component
public class ExceptionShortComponent {

    private final MessageUtilComponent messageUtilComponent;

    public ExceptionShortComponent(MessageUtilComponent messageUtilComponent) {
        this.messageUtilComponent = messageUtilComponent;
    }

    public ExistFoundException existFound(String message, String locale) {
        return new ExistFoundException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NotFoundException notFound(String message, String locale) {
        return new NotFoundException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NotAddSqlException notAddSql(String message, String locale) {
        return new NotAddSqlException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NotUpdateSqlException noUpdateSql(String message, String locale) {
        return new NotUpdateSqlException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NotDeleteSqlException noDeleteSql(String message, String locale) {
        return new NotDeleteSqlException(this.messageUtilComponent.getMessage(message, locale));
    }

    public IsRequiredException isRequired(String message, String locale) {
        return new IsRequiredException(this.messageUtilComponent.getMessage(message, locale));
    }
}
