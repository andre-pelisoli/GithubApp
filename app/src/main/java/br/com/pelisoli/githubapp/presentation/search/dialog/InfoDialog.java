package br.com.pelisoli.githubapp.presentation.search.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import br.com.pelisoli.githubapp.R;

/**
 * Created by pelisoli on 8/27/16.
 */
public class InfoDialog extends DialogFragment {

    public static InfoDialog newInstance(String message) {
        InfoDialog infoDialog = new InfoDialog();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        infoDialog.setArguments(bundle);
        return infoDialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = "";
        Bundle bundle = getArguments();

        if(bundle != null){
            message = bundle.getString("message");
        }

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        builder.setTitle(getString(R.string.dialog_info_header));
        builder.setMessage(message);

        builder.setPositiveButton(getString(R.string.ok), (dialog, which) -> {
            dialog.dismiss();
        });

        return builder.create();
    }

}
