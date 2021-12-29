/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.embedded.hnu.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "NaDesigner",
        id = "org.embedded.hnu.toolbar.SetHardwareToolbarAction"
)
@ActionRegistration(
        iconBase = "org/embedded/hnu/toolbar/icons/icon_Hw24.png",
        displayName = "#CTL_SetHardwareToolbarAction"
)
@ActionReference(path = "Toolbars/NaDesigner", position = 200)
@Messages("CTL_SetHardwareToolbarAction=Set Hardware")
public final class SetHardwareToolbarAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
