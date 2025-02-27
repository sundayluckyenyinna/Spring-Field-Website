package com.springfield.website.utils.pdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import com.springfield.website.utils.FileUtilities;
import com.springfield.website.utils.StringValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenPdfUtility implements PdfUtility{

    @Override
    public String convertToPdf(String htmlString, String pdfDocName) {
        return OpenPdfUtility.convertToStaticPdf(htmlString, pdfDocName);
    }

    public static String convertToStaticPdf(String htmlString, String pdfDocName){
        Document doc = Jsoup.parse(htmlString, Strings.EMPTY, Parser.htmlParser());
        String folderAbsPath = FileUtilities.getDocumentFolderAbsPath();
        String pdfAbsPath = folderAbsPath.concat(File.separator).concat(pdfDocName);
        try (OutputStream os = new FileOutputStream(pdfAbsPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(pdfAbsPath);
            builder.useFastMode();
            builder.useSVGDrawer(new BatikSVGDrawer());
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(doc), StringValues.FORWARD_STROKE);
            builder.run();
        }catch (Exception exception){
            log.error("Exception message: {}", exception.getMessage());
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
        return pdfAbsPath;
    }

    public static String convertToStaticPdf(String resourcePath, Map<String, Object> context, String pdfDocName){
        String htmlString = FileUtilities.downloadAndFormatOuterHtmlFromResource(resourcePath, context);
        return convertToStaticPdf(htmlString, pdfDocName);
    }

    public static String downloadAndConvertHtmlToPdfFromLink(String link, Map<String, Object> contextData, String pdfDocName){
        String htmlString = FileUtilities.downloadOuterHtmlFromHtmlLink(link);
        String formattedHtmlString = FileUtilities.formatHtmlLinkWithSimpleContextBinder(htmlString, contextData);
        return convertToStaticPdf(formattedHtmlString, pdfDocName);
    }
}
