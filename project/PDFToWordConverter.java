import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFToWordConverter {

    public static void main(String[] args) {
        // Path to the PDF file
        String pdfFilePath = "E:\\College Project\\store";

        // Path to the Word file
        String wordFilePath = "E:\\College Project\\output";

        // Convert the PDF to Word
        try {
            convertPDFToWord(pdfFilePath, wordFilePath);
            System.out.println("PDF converted to Word successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void convertPDFToWord(String pdfFilePath, String wordFilePath) throws IOException {
        // Load the PDF document
        PDDocument pdfDocument = PDDocument.load(new File(pdfFilePath));
        // Extract text from the PDF
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfText = pdfStripper.getText(pdfDocument);

        // Create a new Word document
        XWPFDocument wordDocument = new XWPFDocument();

        // Create a paragraph in the Word document and set the extracted text
        XWPFParagraph paragraph = wordDocument.createParagraph();
        paragraph.createRun().setText(pdfText);

        // Write the Word document to the file system
        try (FileOutputStream out = new FileOutputStream(wordFilePath)) {
            wordDocument.write(out);
        }

        // Close the documents
        pdfDocument.close();
        wordDocument.close();
    }
}
