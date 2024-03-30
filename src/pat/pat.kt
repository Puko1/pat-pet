package pat

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.geom.RoundRectangle2D
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.Timer
import kotlin.random.Random


class pat : JFrame() {
    private val timer = Timer(1000) { moveFrame() }

    init {
        title = "p"
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        shape = RoundRectangle2D.Float(75f, 75f, 150f, 150f, 50f, 50f)
        size = Dimension(300, 300)

        val background = JLabel()
        background.icon = ImageIcon("src/imgs/pat.png")

        contentPane.add(background)

        layout = BorderLayout()
        add(background)
        background.setBounds(0, 0, width, height)
        background.horizontalAlignment = JLabel.CENTER
        background.verticalAlignment = JLabel.CENTER

        isVisible = true

        timer.start()
    }

    private fun moveFrame() {
        val newX = Random.nextInt(0, (java.awt.Toolkit.getDefaultToolkit().screenSize.width - width))
        val newY = Random.nextInt(0, (java.awt.Toolkit.getDefaultToolkit().screenSize.height - height))
        setLocation(newX, newY)
    }
}
