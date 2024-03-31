package pat

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.MouseInfo
import java.awt.Robot
import java.awt.geom.RoundRectangle2D
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.Timer

class Pat : JFrame() {

    private var positionY = 0.0
    private var velocityY = 0.0
    private val gravity = 0.07
    private val frameWidth = 150
    private val frameHeight = 150
    private var isOnGround = false

    init {
        title = "p"
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        shape = RoundRectangle2D.Float(75f, 75f, frameWidth.toFloat(), frameHeight.toFloat(), 50f, 50f)
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
            applyGravity(currentX, currentY)
        }

        if (isTouchingGround()) {
            handleGroundCollision()

            val condition = kotlin.random.Random.nextInt(4)
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
                    velocityY = -11.0
                }
            }
        } else {
            isOnGround = false
        }
    }

    private fun applyGravity(x: Int, y: Int) {
        velocityY += gravity
        setLocation(location.x, (location.y + velocityY).toInt())
        if (location.y+225>=y && location.y+75<=y && location.x<=x && location.x+225>=x && velocityY > 0) {
            val robot = Robot()
            robot.mouseMove(x, location.y + 225)
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

    private fun isJumping(y: Int) {
    }
}