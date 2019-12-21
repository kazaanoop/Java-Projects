import java.util.LinkedList;

public class Snake {
    Cell head;
    Cell food;

    public LinkedList<Cell> getBody() {
        return body;
    }

    LinkedList<Cell> body = new LinkedList<>();

    public Cell getHead(){
        return this.body.getFirst();
    }

    public void addTail(Cell last){
        this.body.addLast(last);
    }

    public boolean isEatingFood(Cell food){
        return (Math.abs(getHead().getX()-food.getX())+Math.abs(getHead().getY()-food.getY())==0);
    }

    public Cell move(String direction) {
        Cell cell = null;
        int headX = this.body.getFirst().getX();
        int headY = this.body.getFirst().getY();
        switch(direction) {
            case "UP" :
                cell = new Cell(headX, headY - 1);
                break;
            case "RIGHT" :
                cell = new Cell(headX + 1, headY);
                break;
            case "DOWN" :
                cell = new Cell(headX, headY + 1);
                break;
            case "LEFT" :
                cell = new Cell(headX - 1, headY);
                break;
        }
        this.body.addFirst(cell);
        //System.out.println(body.size());
        Cell c = body.removeLast();
        //System.out.println(body.size());
        return c;

    }

}
