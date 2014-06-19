import cs131223.tron.TronStateUtil;
import tron.Cell;
import junit.framework.TestCase;


public class StudentTests extends TestCase {

	public void testDump(){
		//tests Dump Method
		TronState test = new TronState(3);
		Cell head0 = new Cell(0,0);
		test.setHead(0, head0);
		Cell head1 = new Cell(1,1);
		test.setHead(1, head1);
		String correct = "*..\n.*.\n...";
		String actual = TronStateUtil.dump(test);
		assertTrue(correct.equals(actual));
	}

	public void testToString(){
		//Tests toString Method
		TronState test = new TronState(3);
		Cell head0 = new Cell(0,0);
		test.setHead(0, head0);
		Cell head1 = new Cell(1,1);
		test.setHead(1, head1);
		String correct = "-2,-1,-1,-1,-2,-1,-1,-1,-1";		
		String actual = TronStateUtil.toString(test);
		assertTrue(correct.equals(actual));
	}
}