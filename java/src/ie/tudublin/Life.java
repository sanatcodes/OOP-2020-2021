package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet {

    int size = 100;
    float cellSize;
    boolean[][] board = new boolean[size][size];
    boolean[][] next = new boolean[size][size];

    public int countNeighbours(int row, int col)
    {
        int count = 0;
        
        for(int r = row -1 ; r <= row + 1; r ++)
        {
            for(int c = col -1 ; c <= col + 1; c ++)
            {
                if (! (r == row && c == col))
                {
                    if (getCell(board, r, c))
                    {
                        count ++;
                    }
                }
            }
        }

        // OR Use 8 if statements
        /*
        if (getCell(board, row-1, col-1))
        {
            count ++;
        }
        if (getCell(board, row-1, col))
        {
            count ++;
        }
        if (getCell(board, row-1, col+1))
        {
            count ++;
        }
        if (getCell(board, row, col-1))
        {
            count ++;
        }
        if (getCell(board, row, col+1))
        {
            count ++;
        }
        if (getCell(board, row+1, col-1))
        {
            count ++;
        }
        if (getCell(board, row+1, col))
        {
            count ++;
        }
        if (getCell(board, row+1, col+1))
        {
            count ++;
        }
        */
        
        return count;
    }

    public void setCell(boolean[][] board, int row, int col, boolean b)
    {
        if (row >= 0 && row < size -1 && col >= 0 && col < size -1)
        {
            board[row][col] = b;
        }
    }

    public boolean getCell(boolean[][] board, int row, int col)
    {
        if (row >= 0 && row < size -1 && col >= 0 && col < size -1)
        {
            return board[row][col];
        }
        else
        {
            return false;
        }        
    }

    public void drawBoard(boolean[][] board)
    {
        // Use a nested loop
        // Use map to calculate x and y
        // Rect draw the cell
        // Use the cell size variable
        // Use some colours!
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                float x = map(col, 0, size, 0, width);
                float y = map(row, 0, size, 0, height);
                if (board[row][col])
                {
                    rect(x, y, cellSize, cellSize);
                }
            }
        }

    }

    public void clearBoard(){
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                setCell(board, row, col, false);
            }
        }
    }

    public void crossShape(){
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                setCell(board, row, col, false);
            }
        }
    }

    private void printBoard(boolean[][] board)
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                print(board[row][col] ? 1 : 0);
            }
            println();
        }        
    }

    public void randomize()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                float dice = random(0.0f, 1.0f);
                /*
                if (dice < 0.5)
                {
                    board[row][col] = true;
                }
                else
                {
                    board[row][col] = false;
                }
                */
                board[row][col] = (dice < 0.5f) ? true : false;
            }
        }
    }

    public void settings()
    {
        size(500, 500);
    }
    
    int mode = 0;
    boolean paused = false;
    public void keyPressed() {
        if (keyCode == ' ')
        {
            if (paused){
                paused = false;
            }
            else{
                paused = true;
            }
        }
        
        if (keyCode == '1')
        {
            randomize();
        }
        if (keyCode == '2')
        {
            clearBoard();
        }
        if (keyCode == '3')
        {
        }
            
    }

    public void setup() {
        colorMode(RGB);
        randomize();
        
        /*
        board[0][1] = true;
        board[1][2] = true;
        board[3][2] = true;
        */
        println(countNeighbours(0, 2));

        cellSize = width / (size);
        
        //printBoard(board);        
    }

    

    private void updateBoard()
    {
        if(!paused){
        // Put code here to apply the rules!!
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                //cell is alive
                if(board[row][col]){
                    if(countNeighbours(row, col) == 2 || countNeighbours(row, col) == 3 )
                    {
                        next[row][col] = true;
                    }
                    else
                    {
                        next[row][col] = false;
                    }
                }
                else{
                    if(countNeighbours(row, col) == 3 )
                    {
                        next[row][col] = true;
                    }
                    else
                    {
                        next[row][col] = false;
                    }
                }
            }
        }
        // Swap board and next
        boolean[][] temp = board;
        board = next;
        next = temp;
        }
    }

    public void mouseDragged()
    {
        int r = (int)map(mouseY,0,width,0,size);
        int c = (int)map(mouseX,0,height,0,size);
        setCell(board, r, c, true);
        
    }

    public void draw() {
        background(0);
        drawBoard(board);        
        updateBoard();
        mouseDragged();
    }
}
