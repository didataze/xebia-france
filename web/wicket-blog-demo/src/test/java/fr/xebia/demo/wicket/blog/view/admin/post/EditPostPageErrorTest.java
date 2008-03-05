package fr.xebia.demo.wicket.blog.view.admin.post;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

import fr.xebia.demo.wicket.blog.data.Post;
import fr.xebia.demo.wicket.blog.service.PostService;
import fr.xebia.demo.wicket.blog.service.ServiceException;

public class EditPostPageErrorTest extends ViewPostPageErrorTest {

    protected PostService getPostService() {
        PostService postService = new PostService() {
            @Override
            public Post update(Post entity) throws ServiceException {
                throw new ServiceException(ERROR_MESSAGE);
            }
        };
        return postService;
    }

    @Test
    public void testErrorRender() {

        tester.startPage(PostListPage.class);
        tester.assertRenderedPage(PostListPage.class);
        tester.assertNoErrorMessage();
        tester.assertComponent("posts:0:viewLink", Link.class);
        tester.clickLink("posts:0:viewLink");
        tester.assertRenderedPage(ViewPostPage.class);
        
        tester.assertComponent("editLink", Link.class);
        tester.clickLink("editLink");
        tester.assertRenderedPage(EditPostPage.class);
        tester.assertNoErrorMessage();

        // create the form tester object, mapping to its wicket:id
        FormTester form = tester.newFormTester("postForm");
        // set the parameters for each component in the form
        form.setValue("content", "Test category");
        // all set, submit
        form.submit();
        tester.assertErrorMessages(new String[] { ERROR_MESSAGE });
    }
}
