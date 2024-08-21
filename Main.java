import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(board));

        br.close();
    }

    static public String dfs(int[][] board){
        // dfs 탐색을 위해 stack 사용
        Stack<Node> stack = new Stack<>();

        // 방문 여부 체크를 위한 배열
        boolean[][] visited = new boolean[N][N];
        // (0,0)에서 탐색 시작
        stack.add(new Node(0, 0, board[0][0]));

        while(!stack.isEmpty()){
            Node current = stack.pop();

            int x = current.x;
            int y = current.y;
            int count = current.count;

            // -1인 경우 출력
            if(board[x][y] == -1){
                return "HaruHaru";
            }

            // 방문 여부 체크
            if(visited[x][y]){
                continue;
            }

            // 방문한 경우 방문 체크!
            visited[x][y] = true;

            // x값에서 count만큼 x(행)으로 이동할때 N(전체 크기)보다 크면 안되고, 방문한 값이면 안됨
            if(x + count < N && !visited[x+count][y]){
                stack.add(new Node(x+count, y, board[x+count][y]));
                System.out.println("board[x+count][y] = " + board[x+count][y]);
            }

            // 노드에서 왼, 오 방향으로만 이동할 수 있으므로 x가 안되는 경우 열 방향으로 이동이 가능한지 체크
            if(y + count < N && !visited[x][y+count]){
                stack.add(new Node(x, y+count, board[x][y+count]));
                System.out.println("board[x][y+count] = " + board[x][y+count]);
            }
        }

        return "Hing";
    }

}

// 행, 열, 행열의 값
class Node{
    int x;
    int y;
    int count;

    public Node(int x, int y, int count){
        this.x = x;
        this.y = y;
        this.count = count;
    }
}