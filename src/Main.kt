import java.awt.Dimension
import java.awt.geom.RoundRectangle2D
import javax.swing.JFrame
import javax.swing.Timer
import kotlin.random.Random


class FloatingFrameExample : JFrame() {
    private val timer = Timer(1000) { moveFrame() }

    init {
        title = "Floating Frame Example"
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        shape = RoundRectangle2D.Float(10f, 10f, 150f, 150f, 50f, 50f)
        size = Dimension(300, 300)

        // 타이머 시작
        timer.start()
    }

    private fun moveFrame() {
        // 프레임을 무작위 위치로 이동
        val newX = Random.nextInt(0, (java.awt.Toolkit.getDefaultToolkit().screenSize.width - width))
        val newY = Random.nextInt(0, (java.awt.Toolkit.getDefaultToolkit().screenSize.height - height))
        setLocation(newX, newY)
    }
}

fun main() {
    // Swing 애플리케이션 실행
    FloatingFrameExample().isVisible = true
}
