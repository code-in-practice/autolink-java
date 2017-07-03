package org.nibor.autolink;

import java.util.EnumSet;

/**
 * Desc:
 * User: zsj
 * Date: 17-7-3 下午4:21
 */
public class Test {
    public static void main(String[] args) {
        String s = "你好，我是Infini Studio。刚看到贵公司官网www.yikeart.com，从" +
                "页面布局到细节元素，都抄袭了我们官网的设计，甚至“加入我们”的招聘页都原封不动把我们绘制的icon照搬过去，这样不合适吧？这里是我们网站www.infinistudio.cn";
        System.out.println(filterUrl(s));
    }

    private static String filterUrl(String input) {
        String linkPage = "http://" + "www.zcool.com.cn" + "/url?link=";
        LinkExtractor linkExtractor = LinkExtractor.builder()
                .linkTypes(EnumSet.of(LinkType.URL, LinkType.WWW))
                .build();
        Iterable<LinkSpan> links = linkExtractor.extractLinks(input);
        String result = Autolink.renderLinks(input, links, (link, text, sb) -> {
            sb.append("<a target=\"_blank\" href=\"");
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(text, link.getBeginIndex(), link.getEndIndex());
            sb.append(linkPage);
            sb.append(urlBuilder.toString());
            sb.append("\">");
            sb.append(text, link.getBeginIndex(), link.getEndIndex());
            sb.append("</a>");
        });
        return result;
    }

}
