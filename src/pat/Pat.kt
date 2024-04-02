package pat

import java.awt.*
import java.awt.geom.RoundRectangle2D
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.Timer

class Pat : JFrame() {

    private var positionY = 0.0
    private var velocityY = 0.0
    private var gravity = 1.0f
    private val frameWidth = 150
    private val frameHeight = 150
    private var isOnGround = false
    private var Blackhole = false
    private var Blackholing = false

    init {
        title = "p"
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        shape = RoundRectangle2D.Float(75f, 75f, frameWidth.toFloat(), frameHeight.toFloat(), 75f, 75f)
        size = Dimension(300, 300)
        setLocation(500, 0)
        val timer = Timer(10) { moveFrame() }

        val background = JLabel()
        background.icon = ImageIcon("src/imgs/pat.png")

        contentPane.add(background)

        layout = BorderLayout()
        add(background)

        background.horizontalAlignment = JLabel.CENTER
        background.verticalAlignment = JLabel.CENTER

        positionY = 0.0

        isVisible = true

        timer.start()

        isAlwaysOnTop = true
        requestFocusInWindow()
    }

    private fun moveFrame() {
        if (!isOnGround) {
            val mouseLocation = MouseInfo.getPointerInfo().location
            val currentX = mouseLocation.x
            val currentY = mouseLocation.y
            if (velocityY>0) {
                gravity = 3.0f
            } else {
                gravity = 1.0f
            }
            applyGravity(currentX, currentY)
        }

        if (isTouchingGround()) {
            handleGroundCollision()

            val condition = kotlin.random.Random.nextInt(5)
            println(condition)
            when (condition) {
                0 -> for (i in 50..100) {
                    Thread.sleep(10)
                    setLocation(location.x + 1, location.y)
                    velocityY = 0.0
                }

                1 -> for (i in 50..100) {
                    Thread.sleep(20)
                }

                2 -> for (i in 50..100) {
                    Thread.sleep(10)
                    setLocation(location.x - 1, location.y)
                    velocityY = 0.0
                }

                3 -> {
                    isOnGround = false
                    velocityY = -43.0
                }

                4 -> {
                    isOnGround = false
                    Blackhole = true
                    Blackholing = true
                    velocityY = -0.1
                }
            }
        } else {
            isOnGround = false
        }
    }

    private fun applyGravity(x: Int, y: Int) {
        if (Blackhole) {
            Blackhole = false
            isBlackhole()
        } else if (!Blackholing){
            velocityY += gravity
            setLocation(location.x + (detectX(x)), (location.y + velocityY).toInt())
            if (location.y + 225 >= y && location.y + 75 <= y && location.x + 75 <= x && location.x + 225 >= x && velocityY > 0) {
                val robot = Robot()
                robot.mouseMove(x, location.y + 225)
            }
        }

    }

    private fun isTouchingGround(): Boolean {
        return location.y + height - 75 >= graphicsConfiguration.bounds.height
    }

    private fun handleGroundCollision() {
        setLocation(location.x, graphicsConfiguration.bounds.height - height + 75)
        velocityY = 0.0
        isOnGround = true
    }

    private fun detectX(mouseX: Int): Int {
        if (location.x +75<=mouseX && location.x+225>=mouseX) {
            return 0
        } else {
            if (mouseX > location.x +135 ) {
                return 12
            } else {
                return -12
            }
        }
    }

    private fun isBlackhole() {
        for (i in 1..500) {
            setLocation(location.x, location.y - 1)
            Thread.sleep(1)
        }
        val blackholeX = location.x + 75
        val blackholeY = location.y + 75
        for (i in 1..500) {
            val currentMouseLocation = MouseInfo.getPointerInfo().location
            val dx = (blackholeX - currentMouseLocation.x) / 75
            val dy = (blackholeY - currentMouseLocation.y) / 75
            val newX = currentMouseLocation.x + dx
            val newY = currentMouseLocation.y + dy
            val robot = Robot()
            robot.mouseMove(newX.toInt(), newY.toInt())
            Thread.sleep(10)
        }
        Blackhole = false
        Blackholing = false
    }

}