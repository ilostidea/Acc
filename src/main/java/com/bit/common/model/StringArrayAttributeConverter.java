package com.bit.common.model;

import javax.persistence.AttributeConverter;

public class StringArrayAttributeConverter implements AttributeConverter<String[], String>  {

    public String convertToDatabaseColumn(String[] attribute) {
        String tagString = null;
        if (attribute != null) {
            tagString = "";
            for (int i = 0; i < attribute.length; i++) {
                if (tagString != null && tagString.length() > 0)
                    tagString += "|";
                if (attribute[i] != null && attribute[i].trim().length() > 0)
                    tagString += attribute[i];
            }
        }
        return tagString;
    }

    public String[] convertToEntityAttribute(String attribute) {
        if (attribute != null) {
            return attribute.split("\\|");
        } else
            return new String[0];
    }

}
