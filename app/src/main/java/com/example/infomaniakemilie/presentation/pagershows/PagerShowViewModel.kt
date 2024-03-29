package com.example.infomaniakemilie.presentation.pagershows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.infomaniakemilie.data.local.entity.ShowEntity
import com.example.infomaniakemilie.data.mappers.toShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PagerShowViewModel @Inject constructor(
    pager: Pager<Int, ShowEntity>
): ViewModel() {

    val showPagingFlow = pager.flow
        .map { pagingData ->
            pagingData.map { it.toShow() }
        }
        .cachedIn(viewModelScope)
}