package ca.mohawkcollege.feng.utils;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import ca.mohawkcollege.feng.R;

/**
 * snack bar class
 */
public final class SnackbarUtils {

    private SnackbarUtils() {
    }

    /**
     *error message
     */
    public static void showError(String content, View root) {
        Snackbar.make(root, content + "  Please try again!", Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor(root.getContext(), android.R.color.holo_red_dark))
                .setTextColor(ContextCompat.getColor(root.getContext(), R.color.white))
                .show();
    }

    /**
     *success message
     */
    public static void showSuccess(String content, View root) {
        Snackbar.make(root, content, Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor(root.getContext(), android.R.color.holo_green_dark))
                .setTextColor(ContextCompat.getColor(root.getContext(), R.color.white))
                .show();
    }


    /**
     * show snake bar
     */
    public static void show(boolean success, String msg, View root) {
        if (!success) {
            SnackbarUtils.showError(msg, root);
        } else {
            SnackbarUtils.showSuccess(msg, root);
        }
    }

}
