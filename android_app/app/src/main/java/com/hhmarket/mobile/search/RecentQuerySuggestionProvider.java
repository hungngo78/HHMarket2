package com.hhmarket.mobile.search;

import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.SearchRecentSuggestionsProvider;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;

public class RecentQuerySuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.hhmarket.mobile.RecentQuerySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    //private static String[] matrixCursorColumns = {"_id", "display1", "display2", "query", "date" };
    private static String[] matrixCursorColumns = {"suggest_format",
            SearchManager.SUGGEST_COLUMN_ICON_1,
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            "suggest_intent_query",
            "_id"};

    public RecentQuerySuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor recentCursor = super.query(uri, projection, selection, selectionArgs, sortOrder);
        int count = 0;

        Cursor customCursor = getSearchResultsCursor();


        Cursor[] cursors = new Cursor[] { recentCursor, customCursor};
        MergeCursor mergeCursor = new MergeCursor(cursors);


        return customCursor;
    }

    private MatrixCursor getSearchResultsCursor(){
        MatrixCursor searchResults =  new MatrixCursor(matrixCursorColumns);
        Object[] mCol = new Object[5];
        int counterId = 0;

        for(String rec :  StoresData.getStores()){
            mCol[0] = "";
            mCol[1] = "";
            mCol[2] = rec;
            mCol[3] = rec;
            mCol[4] = ""+counterId++;

            searchResults.addRow(mCol);
        }

        return searchResults;
    }
}