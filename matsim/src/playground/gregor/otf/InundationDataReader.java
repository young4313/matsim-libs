package playground.gregor.otf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

import org.matsim.vis.otfvis.caching.SceneGraph;
import org.matsim.vis.otfvis.data.OTFData.Receiver;
import org.matsim.vis.otfvis.interfaces.OTFDataReader;

public class InundationDataReader extends OTFDataReader {
	
	private final OTFInundationDrawer drawer;
	private Dummy receiver;

	public InundationDataReader() {
		this.drawer = new OTFInundationDrawer();
		Dummy.myDrawer = this.drawer;
	}
	
	@Override
	public void connect(Receiver receiver) {
		this.receiver = (Dummy) receiver;
		
		
	}

	@Override
	public void invalidate(SceneGraph graph) {
//		this.drawer.invalidate(graph);
		this.receiver = new Dummy();
		graph.addItem(this.receiver);
		this.receiver.setTime(graph.getTime());
		
//		this.receiver.invalidate(graph);
	}

	@Override
	public void readConstData(ByteBuffer in) throws IOException {
		
		int size = in.getInt();
		
		 byte[] byts = new byte[size];
		 
		 
		 
		    in.get(byts);
		 
		    
		    ObjectInputStream istream = null;
		 
		    try {
		        istream = new ObjectInputStream(new ByteArrayInputStream(byts));
		        Object obj = istream.readObject();
		 
		        if(obj instanceof InundationData){
		        	this.drawer.setData((InundationData) obj);
		            System.out.println("deserialization successful");
		        }
		    }
		    catch(IOException e){
		        e.printStackTrace();
		    }
		    catch(ClassNotFoundException e){
		        e.printStackTrace();
		    }
//		this.drawer.setData(data);
		
		
		
	}

	@Override
	public void readDynData(ByteBuffer in, SceneGraph graph) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
