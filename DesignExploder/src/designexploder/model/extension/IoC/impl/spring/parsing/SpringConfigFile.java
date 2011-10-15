package designexploder.model.extension.IoC.impl.spring.parsing;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Serializer;

import java.io.*;

public class SpringConfigFile extends Document {

	public SpringConfigFile() {
		super(new Element("root"));
	}
	
	public BeansElement getBeans() {
		Node root = getChild(0);
		return root instanceof BeansElement ? (BeansElement) root : null;
	}

    public InputStream toPrettyXML() {
        PipedInputStream inputStream = null;
        final PipedOutputStream src = new PipedOutputStream();
        try {
            final Serializer serializer = new Serializer(src, "ISO-8859-1");
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            inputStream = new PipedInputStream(src);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serializer.write(SpringConfigFile.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            src.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
	
}
