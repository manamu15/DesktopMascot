import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel{
	final int widthSplit = 3;
	final int heightSplit = 4;

	BufferedImage origin;
	public int width, height;
	private int x, y;
	private int sx, sy;
	
	Panel() {
		setOpaque(false);
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			origin = ImageIO.read(cl.getResource("Mascot.png"));
			width = origin.getWidth() / widthSplit;
			height = origin.getHeight() / heightSplit;
			x = 0;
			y = 0;
			sx = 0;
			sy = 0;
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void SetPosition(int x, int y) {
		sy = 0;
		this.x = x;
		this.y = y;
	}

	public void AddPosition(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void SetImage(int x, int y) {
		sx = x * width;
		sy = y * height;
	}

	public int GetPositionX() {
		return x;
	}

	public int GetPositionY() {
		return y;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			Dimension size = getSize();
			g.setColor(new Color(0, 0, 0, 0f));
			g.fillRect(0, 0, size.width - 1, size.height - 1);
			BufferedImage image = origin.getSubimage(sx, sy, width, height);
			g.drawImage(image, x, y, width, height, this);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}