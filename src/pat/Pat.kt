package pat

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.geom.AffineTransform
import java.awt.geom.RoundRectangle2D
import java.awt.image.BufferedImage
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
        background.setBounds(0, 0, width, height)
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
            applyGravity()
        }

        if (isTouchingGround()) {
            handleGroundCollision()

            val condition = kotlin.random.Random.nextInt(3)
            println(condition)
            if (condition == 0) {
                for (i in 50..100) {
                    Thread.sleep(10) // 1초 대기
                    setLocation(location.x + 1, location.y)
                    velocityY = 0.0
                }
            } else if (condition == 1) {
                for (i in 50..100) {
                    Thread.sleep(10) // 1초 대기
                }
            } else if (condition == 2) {
                for (i in 50..100) {
                    Thread.sleep(10) // 1초 대기
                    setLocation(location.x - 1, location.y)
                    velocityY = 0.0
                }
            }
        } else {
            isOnGround = false
        }
    }

    private fun applyGravity() {
        velocityY += gravity
        setLocation(location.x, (location.y + velocityY).toInt())
    }

    private fun isTouchingGround(): Boolean {
        return location.y + height - 75 >= graphicsConfiguration.bounds.height
    }

    private fun handleGroundCollision() {
        setLocation(location.x, graphicsConfiguration.bounds.height - height + 75)
        velocityY = 0.0
        isOnGround = true
    }

}