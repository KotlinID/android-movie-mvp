package id.kotlin.training.movies.ext

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

class ItemDecoration(
        private val horizontalSpacing: Int,
        private val verticalSpacing: Int,
        private val includeEdge: Boolean
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?,
                                view: View?,
                                parent: RecyclerView?,
                                state: State?) {
        super.getItemOffsets(outRect, view, parent, state)

        fun getGridItemOffsets(outRect: Rect?,
                               position: Int?,
                               column: Int?,
                               spanCount: Int?) {
            val spans = spanCount ?: 0
            val columns = column ?: 0

            if (includeEdge) {
                outRect?.left = horizontalSpacing * (spans - columns) / spans

                if (position ?: 0 < spans) {
                    outRect?.top = verticalSpacing
                }

                outRect?.bottom = verticalSpacing
            } else {
                outRect?.left = horizontalSpacing * columns / spans
                outRect?.right = horizontalSpacing * (spans - 1 - columns) / spans

                if (position ?: 0 >= spans) {
                    outRect?.top = verticalSpacing
                }
            }
        }

        val position = parent?.getChildAdapterPosition(view)
        when (parent?.layoutManager) {
            is GridLayoutManager -> {
                val layoutManager = parent.layoutManager as GridLayoutManager
                val spanCount = layoutManager.spanCount
                val column = position?.rem(spanCount)

                getGridItemOffsets(outRect, position, column, spanCount)
            }
            is StaggeredGridLayoutManager -> {
                val layoutManager = parent.layoutManager as StaggeredGridLayoutManager
                val spanCount = layoutManager.spanCount
                val layoutParams = view?.layoutParams as? StaggeredGridLayoutManager.LayoutParams
                val column = layoutParams?.spanIndex

                getGridItemOffsets(outRect, position, column, spanCount)
            }
            is LinearLayoutManager -> {
                outRect?.left = horizontalSpacing
                outRect?.right = horizontalSpacing

                if (includeEdge) {
                    if (position == 0) {
                        outRect?.top = verticalSpacing
                    }

                    outRect?.bottom = verticalSpacing
                } else {
                    if (position ?: 0 > 0) {
                        outRect?.top = verticalSpacing
                    }
                }
            }
        }
    }
}