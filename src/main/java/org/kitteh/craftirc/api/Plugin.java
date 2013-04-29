package org.kitteh.craftirc.api;

import java.io.File;

import org.kitteh.craftirc.CraftIRC;
import org.kitteh.craftirc.EndPointHandler;

public interface Plugin extends EndPointHandler {
    public CraftIRC getCraftIRC();

    public File getDataFolder();
}