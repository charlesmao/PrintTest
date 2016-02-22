import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class Application {
	public static void test(String code)
	{
	try{
	DocFlavor flavor=DocFlavor.INPUT_STREAM.JPEG;

	//get a printer
	PrintService[] printers=PrintServiceLookup.lookupPrintServices( flavor, null);
	for( int i=0; i<printers.length; i++ ) System.out.println( printers[i].getName());
	PrintService printer=printers[0];

	//job
	DocPrintJob job=printer.createPrintJob();

	//document
	BufferedImage img=new BufferedImage( 400,300, BufferedImage.TYPE_USHORT_555_RGB );
	Graphics g=img.getGraphics();
	g.drawString(code, 100,100);
	ByteArrayOutputStream outstream=new ByteArrayOutputStream();
	ImageIO.write( img, "jpg", outstream);
	byte[] buf=outstream.toByteArray();
	InputStream stream=new ByteArrayInputStream(buf);
	Doc doc=new SimpleDoc(stream,flavor,null);

	//print
	job.print(doc, null);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}
	//Main method
	public static void main(String[] args)
	{
	test("12345");
	}
}
