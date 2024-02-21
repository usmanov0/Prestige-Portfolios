package com.example.prestigeportfoliocreators.errors;

public class CustomErrors {
    // Blogs error
    public static final String BLOG_ID_ERROR = "Invalid 'id', blog not found.";
    public static final String BLOG_TITLE_ERROR = "'title' should be between 3-250 characters.";
    public static final String BLOG_DESCRIPTION_ERROR = "'title' should be between 3-500 characters.";
    public static final String BLOG_BODY_ERROR = "'body' should be between 1-5000 characters.";


    // About me errors
    public static final String ABOUT_ME_TEXT_ERROR = "'text' should be between 1-1000 characters";
    public static final String ABOUT_ME_LOAD_ERROR = "Error loading About me";


    // Message Errors
    public static final String MESSAGE_ID_ERROR = "Invalid 'id', message not found.";
    public static final String MESSAGE_NAME_ERROR = "'name' should be between 3-50 characters.";
    public static final String MESSAGE_CONTACT_INFO_ERROR = "'contactInfo' should be between 6-100 characters.";
    public static final String MESSAGE_BODY_ERROR = "'body' should be between 15-1000 characters.";


    // Project errors
    public static final String PROJECT_NAME_ERROR = "'name' should be between 3-30 characters.";
    public static final String PROJECT_DESCRIPTION_ERROR = "'description' should be between 10-250 characters.";
    public static final String PROJECT_START_DATE_ERROR = "'startDate' should be in format 'MM/YYYY'.";
    public static final String PROJECT_END_DATE_ERROR = "'endDate' should be in format 'MM/YYYY'.";
    public static final String PROJECT_LINK_ERROR = "'link' should be between 4-250 characters.";


    // Skill errors
    public static final String SKILL_NAME_ERROR = "'name' should be between 1-15 characters.";
    public static final String SKILL_ALREADY_EXISTS_ERROR = "Skill already exists.";
    public static final String SKILL_SIMPLE_ICONS_ICON_SLUG_ERROR = "'simpleIconsIconSlug' should be between 0-50 characters.";
    public static final String SKILL_TYPE_ERROR = "Invalid 'type'.";
}
