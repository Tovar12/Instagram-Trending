package com.kogimobile.kogitest;

import android.content.Context;
import android.test.ActivityTestCase;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.test.suitebuilder.annotation.SmallTest;

import com.kogimobile.kogitest.utils.VolleyUtils;

import junit.framework.Assert;

/**
 * Created by
 *
 * @author Felipe Tovar on
 * @date 12/6/15.
 * @about
 */
public class VolleyUtilsTest extends ActivityTestCase{

    Context context;

    /*
    public void setUp() throws Exception {
        super.setUp();

        context = new MockContext();

        setContext(context);

        assertNotNull(context);

    }
    */
    @SmallTest
    public void test_callInstagramService_contextWorking_callSuccessfulyDone(){

        context = getInstrumentation().getContext();
        //Resources testRes = testContext.getResources();
        boolean status = VolleyUtils.updatePostList(context);
        Assert.assertTrue(status);
    }
    /*
    @SmallTest
    public void test_callInstagramService_nullContest_callfails(){
        boolean status = VolleyUtils.updatePostList(null);
        Assert.assertFalse(status);
    }
    */
}
