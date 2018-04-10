package dev.demo.Services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import dev.demo.Entity.*;
import dev.demo.Main;
import dev.demo.Services.SpecialEnums.ColorCategory;
import dev.demo.Services.SpecialEnums.TextEnum;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by bilalsay on 23.12.2017.
 */
public class RaporPrinter {

    private Document document;
    private PdfWriter writer;
    private XMLWorkerHelper worker;
    private String path;

    public RaporPrinter(String path) throws Exception {
        this.path = path;
        this. document = new Document();
        writer = PdfWriter.getInstance(document, new FileOutputStream(path + "_R.pdf"));
        this.worker = XMLWorkerHelper.getInstance();
    }

    public void open() {
        document.open();
    }

    public void newPage() {
        document.newPage();
    }

    public void close() {
        document.close();
    }

    public void printFrontCover(Isemri isemri) throws Exception {
        Mukellef mukellef = isemri.getMukellef();
        String textFrontCover = "<html><head></head><style> ul {list-style-type: none; } ul li {color: #C6A267; font-size: 25px} label { color: #000000} img {width: 482x; height: 117px} </style><body style=\"font-size:12.0pt; font-family: Arial\" >"
                +"<div style=\" width: 690px; text-align:center\" align=\"center\">"
                + "<img src=\""+Main.class.getResource("/logo.png")+"\" style=\"width: 582px; height:127\"/>"
                + "</div>"
                + "<div style=\" padding-top: 750px \">"
                + "<ul>"
                + "<li><label>Mükellef: </label> "+ (mukellef.getUnvan())+"</li>"
                + "<li><label>İnceleme Yılı: </label>"+ isemri.getIncelemeYil() +"</li>"
                + "<li><label>İnceleme Türü: </label>"+ (isemri.getIncelemeTur() == 0 ? TextEnum.SINIRLI.toString() : TextEnum.TAM.toString())+"</li>"
                + "</ul>"
                + "</div>"
                + "</body></html>";

        InputStream iStreamFrontCover = new ByteArrayInputStream(textFrontCover.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, iStreamFrontCover, Charset.forName("UTF-8"), new XMLWorkerFontProvider());
    }

    public void printMukellefIsemri(Isemri isemri) throws Exception {
        Mukellef mukellef = isemri.getMukellef();
        String textMukellefIsemri = "<html><head></head><style>  .lDiv { float: left;width: 135px; color: #000000} .tDiv {float:left; color: #C6A267} </style><body style=\"font-size:12.0pt; font-family: Arial\">"
                +"<div style=\" padding-top: 20px\">"
                + "<div style=\"background-color: #EFF1E9; width: 345px; padding: 5px 10px; height: 400px; float: left;\" align=\"center\">"
                + "<h1 style=\" color: #4F7B82; font-size: 20px; width: 200px;padding-left: 20px \"> Mükellef Bilgileri </h1>"
                + "<div><div class=\"lDiv\">Unvanı/Ad Soyad: </div> <div class=\"tDiv\"> "+mukellef.getUnvan()+"</div></div>"
                + "<div><div class=\"lDiv\">Vergi Kimlik No: </div><div class=\"tDiv\">"+mukellef.getMukellefVergiKimlikNo()+"</div></div>"
                + "<div><div class=\"lDiv\">Tc Kimlik No: </div><div class=\"tDiv\">  "+mukellef.getTcKimlikNo()+"</div></div>"
                + "<div><div class=\"lDiv\">Faaliyet Durumu: </div><div class=\"tDiv\">  "+(mukellef.getFaaliyetDurum() == 0 ? TextEnum.TERK.toString() : TextEnum.FAAL.toString())+"</div></div>"
                + "<div><div class=\"lDiv\">Faaliyet Konusu: </div><div class=\"tDiv\">  "+mukellef.getFaaliyetKonu()+"</div></div>"
                + "<div><div class=\"lDiv\">Email: </div> <div class=\"tDiv\"> "+mukellef.getEmail()+"</div></div>"
                + "<div><div class=\"lDiv\">Telefon: </div> <div class=\"tDiv\"> "+mukellef.getTelefonNo()+"</div></div>"
                + "<div><div class=\"lDiv\">GSM Telefon: </div><div class=\"tDiv\">  "+mukellef.getGsmTelefonNo()+"</div></div>"
                + "<div><div class=\"lDiv\">İkametgah Adresi: </div><div class=\"tDiv\">  "+mukellef.getIkametgahAdres()+"</div></div>"
                + "<div><div class=\"lDiv\">İşyeri Adresi: </div><div class=\"tDiv\">  "+mukellef.getIsYeriAdres()+"</div></div>"
                + "<div><div class=\"lDiv\">Kayıt Tarihi: </div><div class=\"tDiv\">  "+new SimpleDateFormat("dd.MM.yyyy").format(mukellef.getKayitTarih())+"</div></div>"
                + "</div>"
                + "<div style=\"background-color: #EFF1E9; width: 345px;height: 400px; float: left; padding: 5px; \" align=\"center\"> "
                + "<h1 style=\" color: #4F7B82; font-size: 20px; width: 200px;padding-left: 20px\"> İş Emri Bilgileri </h1>"
                + "<div><div class=\"lDiv\">İş Emri No: </div> <div class=\"tDiv\">  "+isemri.getIsemriNo()+"</div></div>"
                + "<div><div class=\"lDiv\">Tarh Vergi Dairesi: </div> <div class=\"tDiv\">  "+isemri.getTarhVergiDaire()+"</div></div>"
                + "<div><div class=\"lDiv\">Konusu: </div> <div class=\"tDiv\">  "+isemri.getKonu()+"</div></div>"
                + "<div><div class=\"lDiv\">İnceleme Türü: </div> <div class=\"tDiv\">  "+(isemri.getIncelemeTur() == 0 ? TextEnum.SINIRLI.toString() : TextEnum.TAM.toString())+"</div></div>"
                + "<div><div class=\"lDiv\">İnceleme Yılı: </div> <div class=\"tDiv\"> "+isemri.getIncelemeYil()+"</div></div>"
                + "<div><div class=\"lDiv\">Başlama Tarihi: </div> <div class=\"tDiv\">  "+new SimpleDateFormat("dd.MM.yyyy").format(isemri.getIsemriYayinTarih())+"</div></div>"
                + "<div><div class=\"lDiv\">Bitiş Tarihi: </div> <div class=\"tDiv\">  "+new SimpleDateFormat("dd.MM.yyyy").format(isemri.getIsemriTamamTarih())+"</div></div>"
                + "<div><div class=\"lDiv\">Kalan Gün: </div> <div class=\"tDiv\">  "+Math.round((isemri.getIsemriTamamTarih().getTime() - Date.valueOf(LocalDate.now()).getTime()) / (double) 86400000)+"</div></div>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        InputStream iStreamMukellefIsemri = new ByteArrayInputStream(textMukellefIsemri.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, iStreamMukellefIsemri, Charset.forName("UTF-8"), new XMLWorkerFontProvider());
    }

    public void printIslem(List<Islem> islemList) throws Exception {
        String textIslem = "<html><head></head><body style=\"font-size:12.0pt; font-family: Arial\">";
        textIslem += "<h1 style=\" color: #4F7B82; font-size: 20px; width: 200px; text-align: center\"> İşlemler </h1>";
        for(Islem islem : islemList) {
            textIslem += "<div>"
                    +"<div>"
                    +"<br/><div style=\"float: left; width: 17px; height: 17px; padding: 0; background-color: "+ ColorCategory.findById(islem.getKategori()).getEnLabel() +" \"></div>"
                    +"<div style=\"float: left; width: 300px;padding-left:10px; padding-bottom: 5px;font-weight: bold; \">Tarih: " + new SimpleDateFormat("dd.MM.yyyy").format(islem.getTamamTarih()) +"</div>"
                    +"</div>"
                    +"<div style=\" background-color: #EFF1E9; padding: 10px \">"
                    +"<p>"+ islem.getIcerik()+"</p>"
                    +"</div>"
                    +"</div>";
        }
        textIslem += "</body></html>";
        InputStream iStreamIslem = new ByteArrayInputStream(textIslem.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, iStreamIslem, Charset.forName("UTF-8"), new XMLWorkerFontProvider());
    }

    public void printNote(List<Note> noteList) throws Exception {
        String textNote = "<html><head></head><body style=\"font-size:12.0pt; font-family: Arial\">";
        textNote += "<h1 style=\" color: #4F7B82; font-size: 20px; width: 200px; text-align: center\"> Notlar </h1>";
        for(Note note : noteList) {
            textNote += "<div>"
                    +"<div>"
                    +"<br/><div style=\"float: left; width: 17px; height: 17px; background-color: "+ ColorCategory.findById(note.getKategori()).getEnLabel() +" \"></div>"
                    +"<div style=\"float: left; width: 300px;padding-left:10px;padding-bottom: 5px; font-weight: bold \">Tarih:  "+ new SimpleDateFormat("dd.MM.yyyy").format(note.getYayinTarih()) +"</div>"
                    +"</div>"
                    +"<div style=\" background-color: #EFF1E9; padding: 10px \">"
                    +"<p>"+ note.getIcerik()+"</p>"
                    +"</div>"
                    +"</div>";
        }
        textNote += "</body></html>";
        InputStream iStreamNote = new ByteArrayInputStream(textNote.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, iStreamNote, Charset.forName("UTF-8"), new XMLWorkerFontProvider());
    }

    public void printMulakat(List<Mulakat> mulakatList) throws Exception {
        String textMulakat = "<html><head></head><body style=\"font-size:12.0pt; font-family: Arial\">";
        textMulakat += "<h1 style=\" color: #4F7B82; font-size: 20px; width: 200px; text-align: center\"> Mülakat </h1>";
        for(Mulakat mulakat : mulakatList) {
            String icerik = mulakat.getIcerik();
            String[] sAr = icerik.split("Cevap:");
            icerik = sAr.length > 1 ? (sAr[0] +"<br/><font color=\"red\">Cevap: </font>" + sAr[1]) : (sAr[0] +"<br/><font color=\"red\">Cevap: </font>");

            textMulakat += "<div>"
                    +"<div>"
                    +"<br/><div style=\" float: left; width: 17px; height: 17px; background-color: "+ ColorCategory.findById(mulakat.getKategori()).getEnLabel() +" \"></div>"
                    +"<div style=\"float: left; width: 300px;padding-left: 10px; padding-bottom: 5px;font-weight: bold \">Tarih:  "+ new SimpleDateFormat("dd.MM.yyyy").format(mulakat.getYayinTarih()) +"</div>"
                    +"</div>"
                    +"<div style=\" background-color: #EFF1E9; padding: 10px \">"
                    +"<p>"+ icerik +"</p>"
                    +"</div>"
                    +"</div>";
        }
        textMulakat += "</body></html>";
        InputStream iStreamMulakat = new ByteArrayInputStream(textMulakat.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, iStreamMulakat, Charset.forName("UTF-8"), new XMLWorkerFontProvider());
    }

    public void addPageNumberToPdf() throws Exception {
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(path + "_R.pdf"), new com.itextpdf.kernel.pdf.PdfWriter(path + "_Rapor.pdf"));
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
        int totalPages = pdfDocument.getNumberOfPages();
        for (int pageNumber = 1; pageNumber <= totalPages; pageNumber++) {
            document.showTextAligned(new Paragraph(pageNumber + "/" + totalPages), document.getPdfDocument().getDefaultPageSize().getWidth() / 2 , 10, pageNumber, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);

        }
        document.close();

        File file = new File(path + "_R.pdf");
        file.delete();
    }
}
