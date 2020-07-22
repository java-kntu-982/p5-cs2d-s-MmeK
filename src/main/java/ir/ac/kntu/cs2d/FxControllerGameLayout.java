package ir.ac.kntu.cs2d;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class FxControllerGameLayout {

    @FXML
    ParallelCamera parallelCamera;

    @FXML
    Canvas canvas;

    @FXML
    AnchorPane anchorPane;

    AnimationTimer animationTimer;
    Image player;
    private Scene scene;

    public FxControllerGameLayout() {

    }


    @FXML
    private void initialize() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.fillText("YOoo",100,200);
        graphicsContext.fillRect(0,0,100,100);
        graphicsContext.lineTo(0,100);

//        SceneManager.getPrimaryStage().getScene().setOnKeyPressed(e-> System.out.println(e.getCode()));
    }
}

//        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        System.out.println();
//
//        Rectangle clipRect = new Rectangle(50, 50, 50, 50);
//        canvas.setClip(clipRect);
//
////        scene.setCamera(parallelCamera);
//
//        try {
//            player = new Image(
//                    new FileInputStream(ResourceHelper.IMAGES + "randomplayer.bmp"));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
////        scene.setOnMouseClicked((e) -> {
////            System.out.println("click");
////            try {
////                animationTimer = null;
////                parallelCamera.setTranslateX(0);
////                parallelCamera.setTranslateY(0);
////                parallelCamera.translateXProperty();
////                StaticSceneLoader.getMainStage().setScene(StaticSceneLoader.getScene(StaticSceneLoader.GAME_LAYOUT));
////
////            } catch (IOException ioException) {
////                ioException.printStackTrace();
////            }
////        });
//
//        ArrayList<String> input = new ArrayList<>();
////
////        StaticSceneLoader.getMainStage().getScene().setOnKeyPressed(
////                e -> {
////                    String code = e.getCode().toString();
////                    // only add once... prevent duplicates
////                    if (!input.contains(code))
////                        input.add(code);
////                    System.out.println(input);
////                });
////
////        StaticSceneLoader.getMainStage().getScene().setOnKeyReleased(
////                e -> {
////                    String code = e.getCode().toString();
////                    input.remove(code);
////                });
//
//
//        final long startNanoTime = System.nanoTime();
//        List<GameObject> gameObjectsList = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//
//            List<GameComponent> birdcomponentList = new ArrayList<>();
//            GameObject bird = new GameObject("bird", graphicsContext, birdcomponentList);
//            GameObject soldier = new GameObject("soldier", graphicsContext, new ArrayList<>());
//            try {
//                Image[] soldierImages = {new Image(new FileInputStream(ResourceHelper.IMAGES + "randomplayer.bmp"))};
//                Image[] imagesArray = {new Image(new FileInputStream(ResourceHelper.IMAGES + "albatross.jpg"))};
//                birdcomponentList.add(new SpriteRenderer(
//                        imagesArray, graphicsContext, bird.getTransform()));
//                soldier.getComponents().add(new SpriteRenderer(soldierImages, graphicsContext, soldier.getTransform()));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            gameObjectsList.add(bird);
//            gameObjectsList.add(soldier);
//        }
//
//        animationTimer = new AnimationTimer() {
//            public void handle(long currentNanoTime) {
//                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//
//                if (input.contains("LEFT")) {
//                    clipRect.setX(clipRect.getX() + (-t));
//                } else if (input.contains("RIGHT")) {
//                    clipRect.setX(clipRect.getX() + (t));
//                }
//
//                if (input.contains("UP")) {
//                    clipRect.setY(clipRect.getY() + (-t));
//                } else if (input.contains("DOWN")) {
//                    clipRect.setY(clipRect.getY() + (t));
//                }
//
//                if (currentNanoTime % 6000 == 0) {
//                    System.out.println("X :" + parallelCamera.getTranslateX());
//                    System.out.println("Y :" + parallelCamera.getTranslateY());
//                }
//                graphicsContext.drawImage(player
//                        , 300 - 16, 200 - 16, 32, 32);
////
////                soldier.getTransform().translate(new Vector2D(t / 10, t / 10));
////                bird.getTransform().rotate(t * 10);
//                for (GameComponent component : gameObjectsList) {
//                    component.update();
//                }
//                // background image clears canvas
//            }
//        };
//        animationTimer.start();
//        Scene scene = StaticSceneLoader.getMainStage().getScene();
//        System.out.println(scene);
//        scene.setOnKeyPressed(e -> System.out.println(e.getCode()));
//    }


//TODO:Different


//private final int tileSize = 10 ;
//    private final int numTilesHoriz = 500 ;
//    private final int numTilesVert = 500 ;
//
//    private final int speed = 400 ; // pixels / second
//    private boolean up ;
//    private boolean down ;
//    private boolean left ;
//    private boolean right ;
//
//    private final int numFilledTiles = numTilesHoriz * numTilesVert / 8 ;
//
//    @Override
//    public void start(Stage primaryStage) {
//        Pane pane = createBackground();
//        Rectangle player = new Rectangle(numTilesHoriz*tileSize/2, numTilesVert*tileSize/2, 10, 10);
//        player.setFill(Color.BLUE);
//        pane.getChildren().add(player);
//
//        Scene scene = new Scene(new BorderPane(pane), 800, 800);
//        Rectangle clip = new Rectangle();
//        clip.widthProperty().bind(scene.widthProperty());
//        clip.heightProperty().bind(scene.heightProperty());
//
//        clip.xProperty().bind(Bindings.createDoubleBinding(
//                () -> clampRange(player.getX() - scene.getWidth() / 2, 0, pane.getWidth() - scene.getWidth()),
//                player.xProperty(), scene.widthProperty()));
//        clip.yProperty().bind(Bindings.createDoubleBinding(
//                () -> clampRange(player.getY() - scene.getHeight() / 2, 0, pane.getHeight() - scene.getHeight()),
//                player.yProperty(), scene.heightProperty()));
//
//        pane.setClip(clip);
//        pane.translateXProperty().bind(clip.xProperty().multiply(-1));
//        pane.translateYProperty().bind(clip.yProperty().multiply(-1));
//
//
//        scene.setOnKeyPressed(e -> {
//            try {
//                processKey(e.getCode(), true);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//        scene.setOnKeyReleased(e -> {
//            try {
//                processKey(e.getCode(), false);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//
//        AnimationTimer timer = new AnimationTimer() {
//            private long lastUpdate = -1 ;
//            @Override
//            public void handle(long now) {
//                long elapsedNanos = now - lastUpdate ;
//                if (lastUpdate < 0) {
//                    lastUpdate = now ;
//                    return ;
//                }
//                double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
//                double deltaX = 0 ;
//                double deltaY = 0 ;
//                if (right) deltaX += speed ;
//                if (left) deltaX -= speed ;
//                if (down) deltaY += speed ;
//                if (up) deltaY -= speed ;
//                player.setX(clampRange(player.getX() + deltaX * elapsedSeconds, 0, pane.getWidth() - player.getWidth()));
//                player.setY(clampRange(player.getY() + deltaY * elapsedSeconds, 0, pane.getHeight() - player.getHeight()));
//                lastUpdate = now ;
//            }
//        };
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        timer.start();
//    }
//
//    private double clampRange(double value, double min, double max) {
//        if (value < min) return min ;
//        if (value > max) return max ;
//        return value ;
//    }
//
//    private void processKey(KeyCode code, boolean on) throws IOException {
//        switch (code) {
//            case SPACE:
//                StaticSceneLoader.getMainStage().setScene(StaticSceneLoader.getScene(StaticSceneLoader.GAME_LAYOUT));
//                break;
//            case LEFT:
//                left = on ;
//                break ;
//            case RIGHT:
//                right = on ;
//                break ;
//            case UP:
//                up = on ;
//                break ;
//            case DOWN:
//                down = on ;
//                break ;
//            default:
//                break ;
//        }
//    }
//
//    private Pane createBackground() {
//
//        List<Integer> filledTiles = sampleWithoutReplacement(numFilledTiles, numTilesHoriz * numTilesVert);
//
//        Canvas canvas = new Canvas(numTilesHoriz * tileSize, numTilesVert * tileSize);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(Color.GREEN);
//
//        Pane pane = new Pane(canvas);
//
//        pane.setMinSize(numTilesHoriz * tileSize, numTilesVert * tileSize);
//        pane.setPrefSize(numTilesHoriz * tileSize, numTilesVert * tileSize);
//        pane.setMaxSize(numTilesHoriz * tileSize, numTilesVert * tileSize);
//
//        for (Integer tile : filledTiles) {
//            int x = (tile % numTilesHoriz) * tileSize ;
//            int y = (tile / numTilesHoriz) * tileSize ;
//            gc.fillRect(x, y, tileSize, tileSize);
//        }
//
//        return pane ;
//    }
//
//    private List<Integer> sampleWithoutReplacement(int sampleSize, int populationSize) {
//        Random rng = new Random();
//        List<Integer> population = new ArrayList<>();
//        for (int i = 0 ; i < populationSize; i++)
//            population.add(i);
//        List<Integer> sample = new ArrayList<>();
//        for (int i = 0 ; i < sampleSize ; i++)
//            sample.add(population.remove(rng.nextInt(population.size())));
//        return sample;
//    }

