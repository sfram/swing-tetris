import java.awt.*; 

class Block {
  final static int SIZE = 20;
  int x, y;
  Color color; 
  Block(int x, int y, Color c) {
    this.x = x;
    this.y = y;
    this.color = c; 
  }
  void draw(Graphics g) {
    int xp = this.x * SIZE; 
    int yp = this.y * SIZE;
    g.setColor(this.color);     
    g.fillRect(xp, yp, SIZE, SIZE); 
    g.setColor(Color.BLACK); 
    g.drawRect(xp, yp, SIZE, SIZE); 
  }
  void move(int dx, int dy) {
    this.x += dx; 
    this.y += dy; 
  }  
  public String toString() {
    return "[" + this.x + ", " + this.y + "]"; 
  }
  void rotateCW(Point c) {
    int newX, newY;
    newX = c.x + c.y - this.y;
    newY = c.y + this.x - c.x; 
    this.x = newX; 
    this.y = newY; 
  }
  public static void main(String[] args) {
    Point center = new Point(8, 3); 
    Block block = new Block(8, 2, Color.RED); 
    System.out.println( block ); 
    block.rotateCW(center); 
    System.out.println(block);
  }
  boolean equals(Block other) {
    return this.x == other.x && this.y == other.y;  
  }
  void setColor(Color c) {
    this.color = c;  
  }
}