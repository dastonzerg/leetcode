
public class KSbTest {
	int _k;
	StringBuilder _sb;
	
	KSbTest(int n) {
		_k=n;
		_sb=new StringBuilder();
	}
	
	void append(char c) {
		_sb.append(c);
	}
	
	void append(StringBuilder sb) {
		_sb.append(sb);
	}
	
	StringBuilder returnSb() {
		StringBuilder returnSb=new StringBuilder();
		for(int i=1; i<=_k; i++) {
			returnSb.append(_sb);
		}
		return returnSb;
	}
}
