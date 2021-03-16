package ru.happyshark.java.ee.products.utils;

public class UI {
    public static String getBootstrap() {
        return "<!DOCTYPE html>" + "<head>" +
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css\"" +
                "rel=\"stylesheet\" integrity=\"sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl\" crossorigin=\"anonymous\">" +
                "</head>" + "<body>" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js\"" +
                "integrity=\"sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0\" crossorigin=\"anonymous\"></script>";
    }

    public static String getHtmlTag(boolean isOpen, String tag, String bootstrapClass) {
        return (isOpen) ? "<" + tag + " class='" + bootstrapClass + "'>" : "</" + tag + ">";
    }

    public static String getTableRow(boolean isHead, Object... cells) {
        StringBuilder sb = new StringBuilder("<tr>");
        String openCellTag = isHead ? "<th>" : "<td>";
        String closeCellTag = isHead ? "</th>" : "</td>";
        for (Object cell : cells) {
            sb.append(openCellTag).append(cell.toString()).append(closeCellTag);
        }
        sb.append("</tr>");
        return sb.toString();
    }

    public static String getHref(String value, String title) {
        return "<a href='" + value + "' class='btn btn-primary'>" + title + "</a>";
    }
}
