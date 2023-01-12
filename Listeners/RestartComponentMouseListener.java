package Chess.Listeners;
//CreateTime: 2022-03-18 11:00 p.m.

import Chess.Parameters.CONSTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;

import java.awt.event.MouseEvent;

public class RestartComponentMouseListener extends MouseListenerAdapter{


    @Override
    public void mouseClicked(MouseEvent e) {
        STATICPARAMETERS.NoneReStartExit = 1;
        CONSTANTPARAMETERS.RESTART_EXIT_COMPONENT.setVisible(false);
    }
}
