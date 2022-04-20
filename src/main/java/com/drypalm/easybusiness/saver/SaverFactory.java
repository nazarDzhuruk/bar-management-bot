package com.drypalm.easybusiness.saver;

public interface SaverFactory {
    Saver save(SaveCategory category);
}
