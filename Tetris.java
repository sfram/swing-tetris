import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class Tetris implements World {
  static final int ROWS = 20; 
  static final int COLUMNS = 10; 
  Tetromino t; 
  SetOfBlocks blocks;
  Tetris(Tetromino t, SetOfBlocks s) {
    this.t = t;
    this.blocks = s;
    blocks.add(new Block(7, ROWS - 1, Color.RED));
    blocks.add(new Block(8, ROWS - 1, Color.GREEN)); 
    blocks.add(new Block(9, ROWS - 1, Color.MAGENTA)); 
    blocks.add(new Block(6, ROWS - 1, Color.BLUE)); 
    blocks.add(new Block(3, ROWS - 1, Color.RED));  
    blocks.add(new Block(4, ROWS - 1, Color.MAGENTA)); 
  }
  public void draw(Graphics g) {
    t.draw(g); 
    blocks.draw(g); 
    g.drawRect(0, 0, Tetris.COLUMNS * Block.SIZE, Tetris.ROWS * Block.SIZE); 
  } 
  public void update() { 
    if (this.landed())
      this.touchdown(); 
    else 
      this.t.move(0, 1); 
  }
  public boolean hasEnded() { return false; } 
  public void keyPressed(KeyEvent e) {
    if (this.landed()) 
      this.touchdown(); 
    else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { this.t.move(-1,  0); }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { this.t.move( 1,  0); } 
    else if (e.getKeyChar() == ' ') { this.jumpDown(); }
    else if (e.getKeyChar() == 'r') {
      this.t.rotateCW();  
    } else this.t.move( 0, 0 );     
  }
  
  public static void main(String[] args) {
    BigBang game = new BigBang(new Tetris(Tetromino.threeL(), new SetOfBlocks())); 
    JFrame frame = new JFrame("Tetris"); 
    frame.getContentPane().add( game ); 
    frame.addKeyListener( game ); 
    frame.setVisible(true); 
    frame.setSize(Tetris.COLUMNS * Block.SIZE + 20, Tetris.ROWS * Block.SIZE + 40); 
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    game.start(); 
  }
  void touchdown() {
    this.blocks = this.blocks.union(this.t.blocks);
    this.blocks.eliminateFullRows(); 
    this.t = Tetromino.pickRandom();
    System.out.println("Min Y " + blocks.minY2(blocks));    
    System.out.println("Min X " + blocks.minX2(blocks));
    System.out.println("Max Y " + blocks.maxY2(blocks));
    System.out.println("Max X " + blocks.maxX2(blocks));
    System.out.println("-----");    
  }
  void jumpDown() {
    if (! this.landed()) { 
      this.t.move(0, 1); 
      this.jumpDown(); 
    }      
  }
  boolean landedOnBlocks() {
    this.t.move(0, 1); 
    if (this.t.overlapsBlocks(this.blocks)) {
      this.t.move(0, -1); 
      return true; 
    } else {
      this.t.move(0, -1); 
      return false; 
    }
  }
  boolean landedOnFloor() {
    return this.t.blocks.maxY() == Tetris.ROWS - 1; 
  }
  boolean landed() {
    return this.landedOnFloor() || this.landedOnBlocks();  
  }
}