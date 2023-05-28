package banco;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyClaseAnadir extends ObjectOutputStream {
	

		   		public MyClaseAnadir(OutputStream out) throws IOException {
			        super(out);
			    }

			    public MyClaseAnadir(FileOutputStream out) throws IOException {
			    	 super(out);
				}

				@Override
			    protected void writeStreamHeader() throws IOException {
			        reset();
			        }		
}
