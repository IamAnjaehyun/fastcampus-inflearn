package algorithm.shortestpath;

class MyGraph {
    private int count;
    private int[][] vertexMatrix;
    private int[] distance; //남아있는 가장 가까운 거리
    private boolean[] visited; // 이미 선택되었는지(거쳐가는 최소의 길이로 선택되었는지)
    private static int UNLIMIT = 9999999;

    public MyGraph(int count) {
        this.count = count;
        vertexMatrix = new int[count][count];
        distance = new int[count];
        visited = new boolean[count];
    }

    public void addEges(int from, int to, int weight) {
        vertexMatrix[from][to] = weight;
        vertexMatrix[to][from] = weight;
    }

    public void calcShortestPath(int from) {
        for (int i = 0; i < count; i++) {
            distance[i] = UNLIMIT;
        }
        visited[from] = true;
        distance[from] = 0;

        for (int i = 0; i < count; i++) {
            if (visited[from] && vertexMatrix[from][i] != 0) {
                distance[i] = vertexMatrix[from][i];
            }
        }

        for (int k = 0; k < count - 1; k++) {
            int min = UNLIMIT;
            int minIndex = -1;

            for (int i = 0; i < count; i++) {
                if (!visited[i] && distance[i] != UNLIMIT) {
                    if (distance[i] < min) {
                        min = distance[i];
                        minIndex = i;
                    }
                }
            }
            visited[minIndex] = true;

            //딕스트라 알고리즘?
            for (int i = 0; i < count; i++) {
                if (!visited[i] && vertexMatrix[minIndex][i] != 0) {
                    if (distance[i] > distance[minIndex] + vertexMatrix[minIndex][i]) {
                        distance[i] = distance[minIndex] + vertexMatrix[minIndex][i];
                    }
                }
            }
        }
    }

    public void showDistance(int from) {
        for (int i = 0; i < count; i++) {
            System.out.println(from + " 노드로부터 " + i + " 노드의 최단 거리는 : " + distance[i]);
        }
    }

}


public class ShortestPath {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph(6);
        graph.addEges(0, 1, 1);
        graph.addEges(0, 2, 4);
        graph.addEges(1, 2, 2);
        graph.addEges(2, 3, 1);
        graph.addEges(3, 4, 8);
        graph.addEges(3, 5, 3);
        graph.addEges(4, 5, 4);

        graph.calcShortestPath(0);
        graph.showDistance(0);
    }

}