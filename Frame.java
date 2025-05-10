import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Frame extends JFrame implements MouseListener, MouseMotionListener {
	
	private Panel panel;
	private int width, height;
	private boolean isDrug;
	
	public Frame() {
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width;
		height = screenSize.height;
		setSize(width,height);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0f));
		panel = new Panel();
		setContentPane(panel);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);

		panel.SetPosition(width / 2, height / 2);
		isDrug = false;
	}

	public void move(int x, int y, int ix, int iy) {
		panel.AddPosition(x, y);
		panel.SetImage(ix, iy);
	}
	
	public boolean outScreen(int mx, int my) {
		int x = panel.GetPositionX();
		int y = panel.GetPositionY();
		if(mx != 0 && (x + mx < 0 || x + mx > width - panel.width)) return true;
		if(my != 0 && (y + my < 0 || y + my > height - panel.height)) return true;
		return false;
	}

	public void PanelRepaint() {
		panel.repaint();
	}

	public boolean IsDrug() {
		return isDrug;
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		isDrug = true;
		System.out.println("CLICK");
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		isDrug = false;
		System.out.println("Release");
	}

	public void mouseMoved(MouseEvent e) { }

	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		panel.SetPosition(p.x, p.y);
	}

	public void mouseClicked(MouseEvent e) { }
	
	public void mouseEntered(MouseEvent e) { }
	
	public void mouseExited(MouseEvent e) { }
}