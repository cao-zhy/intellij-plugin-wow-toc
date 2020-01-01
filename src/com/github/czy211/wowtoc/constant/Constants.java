package com.github.czy211.wowtoc.constant;

public class Constants {
    public static final String DEMO_TEXT = "# This is comment.\n"
            + "## Interface: 80205\n"
            + "## Title: WlkUI\n"
            + "\n"
            + "## Notes: WlkUI\n"
            + "ActionBar.lua\n"
            + "\n"
            + "Auction.lua";

    public static final String[] TAG_NAMES = {"Interface", "Title", "Notes", "Description", "RequiredDep",
            "RequiredDeps", "Dependencies", "Dep", "OptionalDeps", "LoadOnDemand", "LoadWith", "LoadManagers",
            "SavedVariablesPerCharacter", "SavedVariables", "DefaultState", "Secure", "Author", "Version"};
    public static final String[] LOCALIZATION = {"enUS", "enGB", "frFR", "deDE", "esES", "esMX", "itIT", "ptBR", "ruRU",
            "koKR", "zhTW", "zhCN"};

    public static final String REGEX_LOCALIZATION = "enUS|enGB|frFR|deDE|esES|esMX|itIT|ptBR|ruRU|koKR|zhTW|zhCN";
    public static final String REGEX_TAG_NAME = "Interface|Title(-(" + REGEX_LOCALIZATION + "))?|Notes(-("
            + REGEX_LOCALIZATION + "))?|RequiredDep|RequiredDeps|Dependencies|Dep[^: \\n\\f]*|OptionalDeps|"
            + "LoadOnDemand|LoadWith|LoadManagers|SavedVariables|SavedVariablesPerCharacter|DefaultState|Secure|Author|"
            + "Version|X-[^: \\n\\f]+";
    public static final String REGEX_FILE_NAME = ".*\\.([lL][uU][aA]|[xX][mM][lL])$";
}
