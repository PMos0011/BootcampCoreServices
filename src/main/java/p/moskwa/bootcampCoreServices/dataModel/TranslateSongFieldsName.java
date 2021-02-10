package p.moskwa.bootcampCoreServices.dataModel;

public class TranslateSongFieldsName {

    public static String translateFieldNameToPl(String fieldName){
        switch (fieldName){
            case "title":
                return "Tytuł";
            case "author":
                return "Wykonawca";
            case "album":
                return "Album";
            case "category":
                return "Gatunek";
            case "votes":
                return "Głosy";
            default:
                return null;
        }
    }
}