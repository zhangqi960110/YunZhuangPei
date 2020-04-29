package com.pcassem.yunzhuangpei.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFormatUtil {
    public static String getNewContent(String htmltext) {

        String html = "<style>.flex span{\n" +
                "white-space:pre-wrap;\n" +
                "word-wrap : break-word ;\n" +
                "overflow: hidden ;\n" +
                "}</style><div class=\"flex\">" + htmltext + "</div>";

        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }
}
