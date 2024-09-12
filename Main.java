package breackoutball;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Gameplay gameplay=new Gameplay();
		JFrame obj = new JFrame();
        obj.setBounds(0,0,710, 600);
        obj.setTitle("Breakoutball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
	}

}