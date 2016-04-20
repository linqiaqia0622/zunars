package com.zunars.www.net.bean;

/**
 * Created by 洽洽 on 2016/4/19.
 */
public class RoomListPage {
  private int  roomTotal;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getListTotal() {
        return listTotal;
    }

    public void setListTotal(int listTotal) {
        this.listTotal = listTotal;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(int roomTotal) {
        this.roomTotal = roomTotal;
    }

    public int getShowPerPage() {
        return showPerPage;
    }

    public void setShowPerPage(int showPerPage) {
        this.showPerPage = showPerPage;
    }

    private int listTotal;
    private int curPage;
    private int pageTotal;
    private int  pageStart;
    private int  pageEnd;
    private int  showPerPage;

}
